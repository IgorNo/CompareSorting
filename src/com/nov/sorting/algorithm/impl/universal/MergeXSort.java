package com.nov.sorting.algorithm.impl.universal;

import com.nov.sorting.algorithm.interfaces.SortComparator;
import edu.princeton.cs.introcs.StdIn;

import java.util.Comparator;

import static com.nov.sorting.algorithm.utils.AuxiliarySortFunction.*;
import static com.nov.sorting.algorithm.utils.DebugSortFunction.*;

/*************************************************************************
 *  Compilation:  javac MergeXSort.java
 *  Dependencies: StdOut.java StdIn.java
 *
 *  The <tt>MergeXSort</tt> class provides static methods for com.nov.sorting.comparator.sorting an
 *  array using an optimized version of mergesort.
 *************************************************************************/

public class MergeXSort implements SortComparator{
    private static final int CUTOFF = 7;  // cutoff to insertion sort

    private static MergeXSort instance;

    private MergeXSort() { }

    public static MergeXSort getInstance(){
        if (instance == null){
            instance = new MergeXSort();
        }
        return instance;
    }

    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {

        // precondition: src[lo .. mid] and src[mid+1 .. hi] are sorted subarrays
        assert isSorted(src, lo, mid);
        assert isSorted(src, mid+1, hi);

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              dst[k] = src[j++];
            else if (j > hi)               dst[k] = src[i++];
            else if (less(src[j], src[i])) dst[k] = src[j++];   // to ensure stability
            else                           dst[k] = src[i++];
        }

        // postcondition: dst[lo .. hi] is sorted subarray
        assert isSorted(dst, lo, hi);
    }

    private static void merge(Object[] src, Object[] dst, int lo, int mid, int hi, Comparator c) {

        // precondition: src[lo .. mid] and src[mid+1 .. hi] are sorted subarrays
        assert isSorted(src, c, lo, mid);
        assert isSorted(src, c, mid+1, hi);

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                 dst[k] = src[j++];
            else if (j > hi)                  dst[k] = src[i++];
            else if (less(c, src[j], src[i])) dst[k] = src[j++];   // to ensure stability
            else                              dst[k] = src[i++];
        }

        // postcondition: dst[lo .. hi] is sorted subarray
        assert isSorted(dst, c, lo, hi);
    }

    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        // if (hi <= lo) return;
        if (hi <= lo + CUTOFF) { 
            insertionSort(dst, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid+1, hi);

        // if (!less(src[mid+1], src[mid])) {
        //    for (int i = lo; i <= hi; i++) dst[i] = src[i];
        //    return;
        // }

        // using System.arraycopy() is a bit faster than the above loop
        if (!less(src[mid+1], src[mid])) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }

        merge(src, dst, lo, mid, hi);
    }

    private static void sort(Object[] src, Object[] dst, int lo, int hi, Comparator c) {
        // if (hi <= lo) return;
        if (hi <= lo + CUTOFF) { 
            insertionSort(dst, lo, hi, c);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid, c);
        sort(dst, src, mid+1, hi, c);

        // if (!less(src[mid+1], src[mid])) {
        //    for (int i = lo; i <= hi; i++) dst[i] = src[i];
        //    return;
        // }

        // using System.arraycopy() is a bit faster than the above loop
        if (!less(c,src[mid+1], src[mid])) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }

        merge(src, dst, lo, mid, hi, c);
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length-1);  
        assert isSorted(a);
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public void sort(Object[] a, Comparator c) {
    	Object[] aux = a.clone();
        sort(aux, a, 0, a.length-1, c);  
        assert isSorted(a, c);
    }


    // sort from a[lo] to a[hi] using insertion sort
    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }

    // sort from a[lo] to a[hi] using insertion sort
    private static void insertionSort(Object[] a, int lo, int hi, Comparator c) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(c, a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }


    /**
     * Reads in a sequence of strings from standard input; mergesorts them
     * (using an optimized version of mergesort); 
     * and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        getInstance().sort(a);
        show(a);
    }
}