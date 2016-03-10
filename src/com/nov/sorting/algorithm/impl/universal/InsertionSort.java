package com.nov.sorting.algorithm.impl.universal;

import com.nov.sorting.algorithm.interfaces.SortComparator;
import edu.princeton.cs.introcs.StdIn;

import java.util.Comparator;

import static com.nov.sorting.algorithm.utils.AuxiliarySortFunction.*;
import static com.nov.sorting.algorithm.utils.DebugSortFunction.*;

public class InsertionSort implements SortComparator{

    private static InsertionSort instance;

    public static final boolean DEBUG = false;

    private InsertionSort() { }

    public static InsertionSort getInstance(){
        if (instance == null){
            instance = new InsertionSort();
        }
        return instance;
    }

    // use natural order and Comparable interface
    public void sort(Comparable[] a) {
        insertionSort(a, 0, a.length-1);
        assert isSorted(a);
    }

    // use a custom order and Comparator interface
    public void sort(Object[] a, Comparator c) {
        insertionSort(a, 0, a.length-1, c);
        assert isSorted(a, c);
    }

    // return a permutation that gives the elements in a[] in ascending order
    // do not change the original array a[]
    public int[] indexSort(Comparable[] a) {
        int N = a.length;
        int[] index = new int[N];
        for (int i = 0; i < N; i++)
            index[i] = i;

        for (int i = 0; i < N; i++)
            for (int j = i; j > 0 && less(a[index[j]], a[index[j-1]]); j--)
                exch(index, j, j-1);

        return index;
    }

    // sort from a[lo] to a[hi] using insertion sort
    public static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }

    // sort from a[lo] to a[hi] using insertion sort
    public static void insertionSort(Object[] a, int lo, int hi, Comparator c) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(c, a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }

    /**
     * Reads in a sequence of strings from standard input; insertion sorts them;
     * and prints them to standard output in ascending order.
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        InsertionSort.getInstance().sort(a);
        show(a);
    }
}
