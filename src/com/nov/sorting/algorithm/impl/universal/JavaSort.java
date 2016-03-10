package com.nov.sorting.algorithm.impl.universal;

import com.nov.sorting.algorithm.interfaces.SortComparator;

import java.util.Arrays;
import java.util.Comparator;

public class JavaSort implements SortComparator{

    private static JavaSort instance;

    private JavaSort() { }

    public static JavaSort getInstance(){
        if (instance == null){
            instance = new JavaSort();
        }
        return instance;
    }

    @Override
    public void sort(Object[] a, Comparator c) {
        Arrays.sort(a, c);
    }

    @Override
    public void sort(Comparable[] a) {
        Arrays.sort(a);
    }
}
