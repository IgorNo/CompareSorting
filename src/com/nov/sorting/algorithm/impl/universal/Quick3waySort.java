package com.nov.sorting.algorithm.impl.universal;

import com.nov.sorting.algorithm.interfaces.SortComparator;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdRandom;

import java.util.Comparator;
import static com.nov.sorting.algorithm.utils.AuxiliarySortFunction.*;
import static com.nov.sorting.algorithm.utils.DebugSortFunction.*;


/*************************************************************************
 *  Compilation:  javac Quick3waySort.java
 *  Dependencies: StdOut.java StdIn.java
 *
 *  The <tt>Quick3waySort</tt> class provides static methods for com.nov.sorting.comparator.sorting an
 *  array using quicksort with 3-way partitioning.
 *************************************************************************/

public class Quick3waySort implements SortComparator{

    private static Quick3waySort instance;

    private Quick3waySort() { }

    public static Quick3waySort getInstance(){
        if (instance == null){
            instance = new Quick3waySort();
        }
        return instance;
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    public void sort(Object[] a, Comparator c) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1, c);
    }

    // quicksort the subarray a[lo .. hi] using 3-way partitioning
    private static void sort(Comparable[] a, int lo, int hi) { 
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if      (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else              i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]. 
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
        assert isSorted(a, lo, hi);
    }

    private static void sort(Object[] a, int lo, int hi, Comparator c) { 
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Object v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = c.compare(a[i], v);
            if      (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else              i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]. 
        sort(a, lo, lt-1, c);
        sort(a, gt+1, hi, c);
        assert isSorted(a, c, lo, hi);
    }

    /**
     * Reads in a sequence of strings from standard input; 3-way
     * quicksorts them; and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        getInstance().sort(a);
        show(a);
    }

}