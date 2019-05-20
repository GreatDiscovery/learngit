package com.example.mythread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MythreadApplicationTests {
    @Test
    public void testMap() {
      List<String> list = new ArrayList<>();
      list.add("hello");
      list.add("world");
      String[] strArr = {"hello", "world"};
       System.out.println(list.toString());
        System.out.println(strArr.toString());
    }
}

