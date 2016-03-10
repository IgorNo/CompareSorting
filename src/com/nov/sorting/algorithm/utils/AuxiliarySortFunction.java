package com.nov.sorting.algorithm.utils;

import java.util.Comparator;

public class AuxiliarySortFunction {

    /***********************************************************************
     *  Helper sorting functions
     ***********************************************************************/

    // is v < w ?
    public static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // is v < w ?
    public static boolean less(Comparator c, Object v, Object w) {
        return (c.compare(v, w) < 0);
    }

    // does v == w ?
    public static boolean eq(Comparable v, Comparable w) {
        return (v.compareTo(w) == 0);
    }

    // does v == w ?
    public static boolean eq(Comparator c, Object v, Object w) {
        return (c.compare(v, w) == 0);
    }

    // exchange a[i] and a[j]
    public static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // exchange a[i] and a[j]  (for indirect algorithm)
    public static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}
