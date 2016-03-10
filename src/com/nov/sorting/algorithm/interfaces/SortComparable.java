package com.nov.sorting.algorithm.interfaces;

import java.util.Comparator;

public interface SortComparable<T extends Comparable> {

    // use natural order and Comparable interface
    public void sort(T[] a);
}
