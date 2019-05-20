package com.example.mythread.basic;

public class ThreadLocalTest {
    static void print(String str) {
        System.out.println(str + ":" + localVarible.get());
    }

    static ThreadLocal<String> localVarible = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                localVarible.set("one local variable");
                print("threadOne");
                System.out.println("threadOne :" + localVarible.get());
            }
        });
        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                localVarible.set("threadTwo local variable");
                print("threadTwo");
                System.out.println("threadTwo :" + localVarible.get());
            }
        });
        threadOne.start();
        threadTwo.start();
    }
}
