package com.example.mythread.jvm;

/**
 * 子类引用父类静态变量不会触发子类初始化
 */
public class TestFatherInit {
    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}

class SuperClass {
    static {
        System.out.println("superClass init!");
    }

    static int value = 123;
}

class SubClass extends SuperClass {
    static {
        System.out.println("subClass init!");
    }
}
