package com.example.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ApplicationTests {

	@Test
	public void testShort() {
		short a = 128;
		byte b = (byte)a;
		System.out.println(a + "," + b);
	}
}
