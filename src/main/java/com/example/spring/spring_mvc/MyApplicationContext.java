package com.example.spring.spring_mvc;

import com.example.spring.spring_mvc.annotation.MyAutowired;
import com.example.spring.spring_mvc.annotation.MyController;
import com.example.spring.spring_mvc.annotation.MyService;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gavin
 * @date 2018/12/9 14:50
 */
public class MyApplicationContext {

    private Map<String, Object> instanceMapping = new ConcurrentHashMap<>();

    private List<String> classCache = new ArrayList<>();

    public MyApplicationContext(String location) {
        // 定位、加载、注册、初始化、实例化
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream(location);
            Properties config = new Properties();
            config.load(is);
            String packageName = config.getProperty("scanPackage");
            doRegister(packageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        doCreateBean();
        populateBean();
    }

    private void doCreateBean() {
        if (classCache.size() == 0) return;
        try {
            for (String className : classCache) {
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(MyController.class)) {
                    // 类名首字母小写
                    String id = lowerFirstChar(clazz.getSimpleName());
                    instanceMapping.put(id, clazz.newInstance());
                } else if (clazz.isAnnotationPresent(MyService.class)) {
                    MyService service = clazz.getAnnotation(MyService.class);
                    String id = service.value();
                    if (!"".equals(id)) {
                        instanceMapping.put(id, clazz.newInstance());
                        continue;
                    }
                    // 判断是否为接口
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> i : interfaces) {
                        instanceMapping.put(i.getName(), clazz.newInstance());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateBean() {
        if (instanceMapping.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : instanceMapping.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(MyAutowired.class)) continue;;
                MyAutowired myAutowired = field.getAnnotation(MyAutowired.class);
                String id = myAutowired.value().trim();
                if ("".equals(id)) {
                    id = field.getType().getName();
                }
                field.setAccessible(true);
                try {
                    // todo 有问题
                    field.set(entry.getValue(), instanceMapping.get(id));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
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

    public Object getBean(String name) {
        return null;
    }

    public Map<String, Object> getAll() {
        return instanceMapping;
    }

    private String lowerFirstChar(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
