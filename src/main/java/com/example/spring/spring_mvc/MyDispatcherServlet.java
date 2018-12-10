package com.example.spring.spring_mvc;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gavin
 * @date 2018/12/9 11:40
 */
public class MyDispatcherServlet extends HttpServlet {
    public final static String LOCATION = "contextConfigLocation";
    private Map<String, Handler> handlerMap = new HashMap<>();
    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化IOC容器
        MyApplicationContext context = new MyApplicationContext(config.getInitParameter(LOCATION));
        initMultipartResolver(context);
        initLocaleResolver(context);
        initThemeResolver(context);
        // 处理URL和method的关系
        initHandlerMappings(context);
        // 适配器
        initHandlerAdapters(context);
        initHandlerExceptionResolvers(context);
        initRequestToViewNameTranslator(context);
        initViewResolvers(context);
        initFlashMapManager(context);
    }

    private void initMultipartResolver(MyApplicationContext context) {};
    private void initLocaleResolver(MyApplicationContext context) {};
    private void initThemeResolver(MyApplicationContext context) {};
    private void initHandlerMappings(MyApplicationContext context) {};
    private void initHandlerAdapters(MyApplicationContext context) {};
    private void initHandlerExceptionResolvers(MyApplicationContext context) {};
    private void initRequestToViewNameTranslator(MyApplicationContext context) {};
    private void initViewResolvers(MyApplicationContext context) {};
    private void initFlashMapManager(MyApplicationContext context) {};

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

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        Handler handler = getHandler();
        if (handler == null) {
            resp.getWriter().write("404 Not Found");
            return;
        }
        HandlerAdapt adapt = getHandlerAdapt(handler);
        adapt.handle(req, resp, handler);
    }

    private Handler getHandler() {
        return null;
    }

    private HandlerAdapt getHandlerAdapt(Handler handler) {
        return null;
    }
    private class Handler {

    }
    private class HandlerAdapt {
        public void handle(HttpServletRequest req, HttpServletResponse resp, Handler handler) {

        }
    }

}
