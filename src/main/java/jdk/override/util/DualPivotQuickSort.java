//package jdk.override.util;
//
///**
// * 不写了，这个里面a,b用的太多了
// *
// * @author gavin
// * @date 2019/3/1 14:48
// */
//final class DualPivotQuickSort {
//    private DualPivotQuickSort() {
//    }
//
//    private static final int MAX_RUN_COUNT = 67;
//    private static final int MAX_RUN_LENGTH = 33;
//    private static final int QUICKSORT_THRESHOLD = 286;
//
//    static void sort(int[] a, int left, int right, int[] work, int workBase, int workLen) {
//        if (right - left < QUICKSORT_THRESHOLD) {
//            // 小数组用快排
//            sort(a, left, right, true);
//            return;
//        }
//
//        int[] run = new int[MAX_RUN_COUNT + 1];
//        int count = 0;
//        run[0] = left;
//        // 检查数组是否已经有部分顺序了
//        for (int k = left; k < right; run[count] = k) {
//            // 升序
//            if (a[k] < a[k + 1]) {
//                // 一直找到打破升序规则的index
//                while (++k <= right && a[k - 1] <= a[k]) ;
//                // 降序
//            } else if (a[k] > a[k + 1]) {
//                while (++k <= right && a[k - 1] >= a[k]) ;
//                // 如果是降序的，找到k后将数组倒排
//                for (int lo = run[count] - 1, hi = k; ++lo < --hi; ) {
//                    int t = a[lo];
//                    a[lo] = a[hi];
//                    a[hi] = t;
//                }
//            } else {
//                // 数组中有这么多相等的值时直接使用快排
//                for (int m = MAX_RUN_LENGTH; ++k <= right && a[k - 1] == a[k]; ) {
//                    if (--m == 0) {
//                        sort(a, left, right, true);
//                        return;
//                    }
//                }
//            }
//            // count用来统计数组中有序数列的个数，如果达到了最大个数说明该数组不是高度有序的，我们直接用快排
//            if (++count == MAX_RUN_COUNT) {
//                sort(a, left, right, true);
//                return;
//            }
//        }
//
//        if (run[count] == right++) {
//            run[++count] = right;
//        } else if (count == 1)
//            return;
//
//        byte odd = 0;
//        for (int n = 1; (n <<= 1) < count; odd ^= 1) ;
//
//        int[] b;
//        int ao, bo;
//        int blen = right - left;
//        if (work == null || workLen < blen || workBase + blen > work.length) {
//            work = new int[blen];
//            workBase = 0;
//        }
//        if (odd == 0) {
//            System.arraycopy(a, left, work, workBase, blen);
//            b = a;
//            bo = 0;
//            a = work;
//            ao = workBase - left;
//        } else {
//            b = work;
//            ao = 0;
//            bo = workBase - left;
//        }
//
//        for (int last; count > 1; count = last) {
//            for (int k = (last = 0) + 2; k <= count; k += 2) {
//                int hi = run[k], mi = run[k - 1];
//                for (int i = run[k - 2], p = i, q = mi; i < hi; ++i) {
//                    if (q >= hi || p < mi && a[p + ao] <= q[q + ao])
//                }
//            }
//        }
//    }
//}
