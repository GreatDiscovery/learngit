package com.example.mytomcat;

import java.io.IOException;

public class HelloServlet extends MyServlet {
    @Override
    void doGet(MyRequest myRequest, MyResponse myResponse)  {
        try {
            myResponse.write("get hello world");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("post hello world");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
