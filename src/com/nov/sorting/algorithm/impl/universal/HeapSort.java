package com.nov.sorting.algorithm.impl.universal;

import com.nov.sorting.algorithm.interfaces.SortComparator;
import edu.princeton.cs.introcs.StdOut;

import java.util.Comparator;

/*************************************************************************
 *  Compilation:  javac HeapSort.java
 *  Dependencies: StdOut.java StdIn.java
 *
 *  The <tt>HeapSort</tt> class provides a static methods for heapsorting
 *  an array.
 *************************************************************************/

public class HeapSort implements SortComparator{

    private static HeapSort instance;

    private HeapSort() { }

    public static HeapSort getInstance(){
        if (instance == null){
            instance = new HeapSort();
        }
        return instance;
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param pq the array to be sorted
     */
    public  void sort(Comparable[] pq) {
        int N = pq.length;
        for (int k = N/2; k >= 1; k--)
            sink(pq, k, N);
        while (N > 1) {
            exch(pq, 1, N--);
            sink(pq, 1, N);
        }
    }

    public void sort(Object[] pq, Comparator c) {
        int N = pq.length;
        for (int k = N/2; k >= 1; k--)
            sink(pq, k, N,c);
        while (N > 1) {
            exch(pq, 1, N--);
            sink(pq, 1, N,c);
        }
    }

   /***********************************************************************
    * Helper functions to restore the heap invariant.
    **********************************************************************/

    private static void sink(Comparable[] pq, int k, int N) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    private static void sink(Object[] pq, int k, int N, Comparator c) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(pq, c, j, j+1)) j++;
            if (!less(pq, c, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

   /***********************************************************************
    * Helper functions for comparisons and swaps.
    * Indices are "off-by-one" to support 1-based indexing.
    **********************************************************************/
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static boolean less(Object[] pq, Comparator c, int i, int j) {
        return c.compare(pq[i-1],pq[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }


   /***********************************************************************
    *  Check if array is sorted - useful for debugging
    ***********************************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }


    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }
}