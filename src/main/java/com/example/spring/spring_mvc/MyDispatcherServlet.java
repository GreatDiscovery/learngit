package com.example.spring.spring_mvc;


import com.example.spring.spring_mvc.annotation.MyController;
import com.example.spring.spring_mvc.annotation.MyRequestMapping;
import com.example.spring.spring_mvc.annotation.MyRequestParam;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author gavin
 * @date 2018/12/9 11:40
 */
public class MyDispatcherServlet extends HttpServlet {
    public final static String LOCATION = "contextConfigLocation";
    private Map<String, Handler> handlerMapping = new HashMap<>();
    private Map<Handler, HandlerAdapt> adaptMapping = new HashMap<>();

    //    private List<Handler> handlerMappings = new ArrayList<>();
    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化IOC容器
        MyApplicationContext context = new MyApplicationContext(config.getInitParameter(LOCATION));
        initMultipartResolver(context);
        initLocaleResolver(context);
        initThemeResolver(context);
        // 处理URL和method的关系
        initHandlerMappings(context);
        // 适配器，用来动态匹配参数，动态赋值
        initHandlerAdapters(context);
        initHandlerExceptionResolvers(context);
        initRequestToViewNameTranslator(context);
        initViewResolvers(context);
        initFlashMapManager(context);
    }

    private void initMultipartResolver(MyApplicationContext context) {
    }

    ;

    private void initLocaleResolver(MyApplicationContext context) {
    }

    ;

    private void initThemeResolver(MyApplicationContext context) {
    }

    ;

    private void initHandlerMappings(MyApplicationContext context) {
        Map<String, Object> ioc = context.getAll();
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(MyController.class)) {
                continue;
            }

            String url = "";
            if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
                url = requestMapping.value();
            }
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(MyRequestMapping.class)) {
                    MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
                    String mappingUrl = url + requestMapping.value();
                    handlerMapping.put(mappingUrl, new Handler(entry.getValue(), method));
                }
            }
        }
    }

    private void initHandlerAdapters(MyApplicationContext context) {
        if (handlerMapping.isEmpty()) {
            return;
        }
        Map<String, Integer> paramMapping = new HashMap<>();
        for (Map.Entry<String, Handler> entry : handlerMapping.entrySet()) {
            Class<?>[] paramsTypes = entry.getValue().method.getParameterTypes();
            for (int i = 0; i < paramsTypes.length; i++) {
                Class<?> type = paramsTypes[i];
                if (type == HttpServletRequest.class || type == HttpServletResponse.class) {
                    paramMapping.put(type.getName(), i);
                }
            }
            // todo 不太懂原理
            Annotation[][] pa = entry.getValue().method.getParameterAnnotations();
            for (int i = 0; i < pa.length; i++) {
                for (Annotation a : pa[i]) {
                    if (a instanceof MyRequestParam) {
                        String paramName = ((MyRequestParam) a).value();
                        if ("".equals(paramName.trim())) {
                            paramMapping.put(paramName, i);
                        }
                    }
                }
            }
            adaptMapping.put(entry.getValue(), new HandlerAdapt(paramMapping));
        }
    }

    private void initHandlerExceptionResolvers(MyApplicationContext context) {
    }

    ;

    private void initRequestToViewNameTranslator(MyApplicationContext context) {
    }

    ;

    private void initViewResolvers(MyApplicationContext context) {
    }

    ;

    private void initFlashMapManager(MyApplicationContext context) {
    }

    ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatcher(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception, Msg :" + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Handler handler = getHandler(req);
        if (handler == null) {
            resp.getWriter().write("404 Not Found");
            return;
        }
        HandlerAdapt ha = getHandlerAdapt(handler);
        ha.handle(req, resp, handler);
    }

    private Handler getHandler(HttpServletRequest req) {
        if (handlerMapping.isEmpty()) {
            return null;
        }
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        return handlerMapping.get(url);
    }

    private HandlerAdapt getHandlerAdapt(Handler handler) {
        if (adaptMapping.isEmpty()) {
            return null;
        }
        return adaptMapping.get(handler);
    }

    private class Handler {
        protected Object controller;
        protected Method method;

        Handler(Object controller, Method method) {
            this.controller = controller;
            this.method = method;
        }

    }

    private class HandlerAdapt {
        Map<String, Integer> paramMapping;

        public HandlerAdapt(Map<String, Integer> paramMapping) {
            this.paramMapping = paramMapping;
        }
        // 利用反射调用URL的方法，invoke需要对象和参数列表
        public void handle(HttpServletRequest req, HttpServletResponse resp, Handler handler) throws Exception{
            // 传request、response、handler作用
            Class<?>[] paramTypes = handler.method.getParameterTypes();
            // 反射全部值一块赋值给args
            Object[] paramValues = new Object[paramTypes.length];
            Map<String, String[]> params = req.getParameterMap();
            for (Map.Entry<String, String[]> param : params.entrySet()) {
                String value = Arrays.toString(param.getValue()).replaceAll("\\[ | \\]", "").replaceAll(",\\s", ",");

                if (!this.paramMapping.containsKey(param.getKey())) {continue;}
                int index = this.paramMapping.get(param.getKey());

                paramValues[index] = castStringValue(value, paramTypes[index]);
                int reqIndex = this.paramMapping.get(HttpServletRequest.class);
                paramValues[reqIndex] = req;
                int respIndex = this.paramMapping.get(HttpServletResponse.class);
                paramValues[respIndex] = resp;
                handler.method.invoke(handler.controller, paramValues);
            }

        }

        private Object castStringValue(String value, Class<?> clazz) {
            if (clazz == String.class) {
                return value;
            } else if (clazz == Integer.class) {
                return Integer.valueOf(value);
            } else if (clazz == int.class) {
                return Integer.valueOf(value).intValue();
            } else {
                return null;
            }
        }

    }

}
