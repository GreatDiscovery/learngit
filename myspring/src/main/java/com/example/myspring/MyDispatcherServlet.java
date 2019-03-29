package com.example.myspring;

import com.example.myspring.support.Handler;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author gavin
 * @date 2019/3/27 13:56
 */
public class MyDispatcherServlet extends HttpServlet {

    public final static String LOCATION = "contextConfigLocation";
    private MyApplicationContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = new MyApplicationContext(config.getInitParameter(LOCATION));
        System.out.println("My MVC framework is init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.doDispatch(req, resp);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Object doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        if (!context.handlerMap.containsKey(url)) {
            resp.getWriter().print("404 NOT FOUND!");
            return null;
        }

        Handler handler = context.handlerMap.get(url);
        System.out.println("获得URL对应的方法" + url);
        Object invoke = handler.getMethod().invoke(handler.getController(), null);
        return invoke;
    }
}
