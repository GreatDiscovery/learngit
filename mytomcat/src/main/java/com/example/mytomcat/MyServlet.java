package com.example.mytomcat;

public abstract class MyServlet {
    abstract void doGet(MyRequest myRequest, MyResponse myResponse);

    abstract void doPost(MyRequest myRequest, MyResponse myResponse);

    public void service(MyRequest myRequest, MyResponse myResponse) {
        if (myRequest.getMethod().equalsIgnoreCase("POST")) {
            doPost(myRequest, myResponse);
        } else {
            doGet(myRequest, myResponse);
        }
    }
}
