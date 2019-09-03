package com.example.myspring;

import com.example.myspring.annotation.MyAutowired;
import com.example.myspring.annotation.MyController;
import com.example.myspring.annotation.MyRequestMapping;
import com.example.myspring.annotation.MyService;
import com.example.myspring.support.Handler;
import javafx.beans.binding.ObjectExpression;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gavin
 * @date 2019/3/27 12:27
 */
public class MyApplicationContext {

    protected List<String> classCache = new ArrayList<>();
    protected Map<String, Object> instanceMapping = new ConcurrentHashMap<>();
    protected Map<String, Handler> handlerMap = new ConcurrentHashMap<>();

    public MyApplicationContext(String location) {
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream(location);
            Properties config = new Properties();
            config.load(is);
            String packageName = config.getProperty("scanPackage");
            doRegister(packageName);
            doCreateBean();
            populateBean();
            initHandlerMapping();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doRegister(String packageName) {
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());

        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                doRegister(packageName + "." + file.getName());
            } else {
                classCache.add(packageName + "." + file.getName().replace(".class", "").trim());
            }
        }
    }

    private void doCreateBean() {
        if (classCache.size() == 0) return;
        try {
            for (String className : classCache) {
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(MyController.class)) {
                    String id = lowerFirstChar(clazz.getSimpleName());
                    instanceMapping.put(id, clazz.newInstance());
                } else if (clazz.isAnnotationPresent(MyService.class)) {
                    MyService myService = clazz.getAnnotation(MyService.class);
                    String id = myService.value();
                    if (!"".equals(id)) {
                        instanceMapping.put(id, clazz.newInstance());
                        continue;
                    }
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> i : interfaces) {
                        instanceMapping.put(i.getName(), clazz.newInstance());
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    private void populateBean() {
        if (instanceMapping.isEmpty()) return;
        for (Map.Entry<String, Object> entry : instanceMapping.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(MyAutowired.class)) {
                    continue;
                }
                MyAutowired myAutowired = field.getAnnotation(MyAutowired.class);
                String beanName = myAutowired.value().trim();
                if ("".equals(beanName)) {
                    beanName = lowerFirstChar(field.getType().getSimpleName());
                }
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(), instanceMapping.get(beanName));
                    System.out.println(entry.getValue() + "is autowired, object is" + instanceMapping.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                }
            }

        }
    }

    private void initHandlerMapping() {
        if (instanceMapping.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : instanceMapping.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(MyController.class)) {
                continue;
            }
            String url = "";
            if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                MyRequestMapping myRequestMapping = clazz.getAnnotation(MyRequestMapping.class);
                url = myRequestMapping.value().trim();
            }

            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(MyRequestMapping.class)) {
                    continue;
                }
                MyRequestMapping myRequestMapping = method.getAnnotation(MyRequestMapping.class);
                String murl = url + myRequestMapping.value().trim();

                Handler handler = new Handler();
                handler.setController(entry.getValue());
                handler.setMethod(method);
                handlerMap.put(murl, handler);
                System.out.println("Mapping:" + murl + " " + handler);
            }
        }
    }

    private String lowerFirstChar(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
