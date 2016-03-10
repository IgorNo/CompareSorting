package com.nov.sorting.algorithm.interfaces;

import java.util.Comparator;

public interface SortComparator extends SortComparable{
    // use a custom order and Comparator interface
    public void sort(Object[] a, Comparator c);
}
