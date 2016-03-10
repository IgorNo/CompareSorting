package com.nov.sorting.algorithm.impl.universal;

import com.nov.sorting.algorithm.interfaces.SortComparator;
import edu.princeton.cs.introcs.StdIn;

import java.util.Comparator;

import static com.nov.sorting.algorithm.utils.AuxiliarySortFunction.*;
import static com.nov.sorting.algorithm.utils.DebugSortFunction.*;


/*************************************************************************
 *  Compilation:  javac MergeBUSort.java
 *  Dependencies: StdOut.java StdIn.java
 *   
 *  The <tt>MergeBUSort</tt> class provides static methods for com.nov.sorting.comparator.sorting an
 *  array using bottom-up mergesort.
 *   
 *************************************************************************/

public class MergeBUSort implements SortComparator{

    private static MergeBUSort instance;

    private MergeBUSort() { }

    public static MergeBUSort getInstance(){
        if (instance == null){
            instance = new MergeBUSort();
        }
        return instance;
    }

    // stably merge a[lo..m] with a[m+1..hi] using aux[lo..hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int m, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }

        // merge back to a[]
        int i = lo, j = m+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > m)                a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }

    }

    // stably merge a[lo..m] with a[m+1..hi] using aux[lo..hi]
    private static void merge(Object[] a, Object[] aux, int lo, int m, int hi, Comparator c) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }

        // merge back to a[]
        int i = lo, j = m+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > m)                  a[k] = aux[j++];
            else if (j > hi)                 a[k] = aux[i++];
            else if (less(c,aux[j], aux[i])) a[k] = aux[j++];
            else                             a[k] = aux[i++];
        }

    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int n = 1; n < N; n = n+n) {
            for (int i = 0; i < N-n; i += n+n) {
                int lo = i;
                int m  = i+n-1;
                int hi = Math.min(i+n+n-1, N-1);
                merge(a, aux, lo, m, hi);
            }
        }
        assert isSorted(a);
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public void sort(Object[] a, Comparator c) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int n = 1; n < N; n = n+n) {
            for (int i = 0; i < N-n; i += n+n) {
                int lo = i;
                int m  = i+n-1;
                int hi = Math.min(i+n+n-1, N-1);
                merge(a, aux, lo, m, hi, c);
            }
        }
        assert isSorted(a, c);
    }


    /**
     * Reads in a sequence of strings from standard input; bottom-up
     * mergesorts them; and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        getInstance().sort(a);
        show(a);
    }
}