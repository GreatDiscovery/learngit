package com.example.mytomcat;

import java.util.ArrayList;
import java.util.List;

public class ServletMappingConfig {
    public static List<ServletMapping> servletMappings = new ArrayList<>();
    static {
        servletMappings.add(new ServletMapping("helloworld", "/hello", "com.example.mytomcat.HelloServlet"));
    }
}
