package com.example.algorithm;

import java.util.Arrays;

/**
 * @author gavin
 * @date 2019/3/28 8:19
 */
public class QuickSort {

    public void sort(int[] arr, int low, int high) {
        if (low > high) return;
        int i = low, j = high;
        int pivotKey = arr[low];
        while (i < j) {
            while (i < j && arr[j] > pivotKey) {
                j--;
            }
            if (i < j) {
                arr[i++] = arr[j];
            }
            while (i < j && arr[i] < pivotKey) {
                i++;
            }
            if (i < j) {
                arr[j--] = arr[i];
            }
        }
        arr[i] = pivotKey;
        sort(arr, low, i - 1);
        sort(arr, i + 1, high);
    }
}

class TestQuickSort {
    public static void main(String[] args) {
        int[] arr = {4, 1, 7, 9, 3, 5, 2, 0, 8};
        new QuickSort().sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}