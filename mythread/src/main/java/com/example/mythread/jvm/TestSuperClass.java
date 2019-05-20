package com.example.mythread.jvm;

public class TestSuperClass {
    // 这块为什么没有初始化
    public TestSuperClass() {
        System.out.println("TestSuperClass init!");
    }

    // main方法是通过类名.static方法直接调用的，所以不会初始化main方法包含的类
    public static void main(String[] args) {
        Sub sup = new Sub();
    }
}

class Super {
    public Super() {
        System.out.println("Super init!");
    }
}

class Sub extends Super {
    public Sub() {
        System.out.println("Sub init!");
    }
}

class Hello {
    static {
        System.out.println("hello world!");
    }

    public static void main(String[] args) {
        System.exit(0);
    }
}