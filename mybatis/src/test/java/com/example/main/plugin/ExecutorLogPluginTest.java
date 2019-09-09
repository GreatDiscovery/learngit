package com.example.main.plugin;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ExecutorLogPluginTest {


    static int[] a = {1, 5, 7,22,104, 57, 9};
    public static void main(String[] args) {
        ExecutorLogPluginTest ex = new ExecutorLogPluginTest();
        System.out.println(Arrays.toString(ex.getNums(a, 8)));
    }

    public int[] getNums(int[] arr, int count) {
        int[] newArr = new int[count];
        if (count == 0 || arr == null || count > arr.length) return newArr;
        Random random = new Random();
        Set<Integer> set = new HashSet<>(count);
        int index = 0;
        while (count > 0) {
            int tmp = random.nextInt(arr.length);
            if (!set.contains(tmp)) {
                newArr[index++] = arr[tmp];
                set.add(tmp);
                count--;
            }
        }
        return newArr;
    }

    public int[] get(int[] arr, int count) {

        return null;
    }

}