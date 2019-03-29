package com.example.algorithm;

import java.util.Arrays;

/**
 * @author gavin
 * @date 2019/3/28 8:19
 */
public class MergeSort {
    public int[] mergeSort(int[] arr, int low, int high) {
        if (low == high) return new int[]{arr[low]};
        int mid = (low + high) >> 1;
        int[] left = mergeSort(arr, low, mid);
        int[] right = mergeSort(arr, mid + 1, high);
        return merge(left, right);
    }

    public int[] merge(int[] left, int[] right) {
        int[] arr = new int[left.length + right.length];
        int p = 0, lp = 0, rp = 0;
        while (lp < left.length && rp < right.length) {
            if (left[lp] < right[rp]) {
                arr[p++] = left[lp++];
            } else {
                arr[p++] = right[rp++];
            }
        }
        while (lp < left.length) {
            arr[p++] = left[lp++];
        }
        while (rp < right.length) {
            arr[p++] = right[rp++];
        }
        return arr;
    }

}

class TestMergeSort {
    public static void main(String[] args) {
        int[] arr = {4, 1, 7, 9, 3, 5, 2, 0, 8};
        int[] newArr = new MergeSort().mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(newArr));
    }
}