package jdk.override.util;

import java.util.Comparator;

/**
 * @author gavin
 * @date 2019/3/1 14:23
 */
public class MyArrays {
    private static final int MIN_ARRAY_SORT_GRAN = 1 << 13;
    private MyArrays() {}
    static final class NaturalOrder implements Comparator<Object> {
       public int compare(Object first, Object second) {
           return ((Comparable<Object>)first).compareTo(second);
       }
        static final NaturalOrder INSTANCE = new NaturalOrder();
    }

    public static void sort(int[] a) {
        DualPivotQuickSort.sort(a, 0, a.length - 1, null, 0, 0);
    }
}
