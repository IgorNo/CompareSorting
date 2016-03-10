package com.nov.sorting.algorithm.impl.universal;

import com.nov.sorting.algorithm.interfaces.SortComparator;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

import java.util.Comparator;
import static com.nov.sorting.algorithm.utils.AuxiliarySortFunction.*;
import static com.nov.sorting.algorithm.utils.DebugSortFunction.*;


/*************************************************************************
 *  Compilation:  javac QuickSort.java
 *  Dependencies: StdOut.java StdIn.java
 *
 *  The <tt>QuickSort</tt> class provides static methods for com.nov.sorting.comparator.sorting an
 *  array and selecting the ith smallest element in an array using quicksort.
 *************************************************************************/

public class QuickSort implements SortComparator{

    private static QuickSort instance;

    private QuickSort() { }

    public static QuickSort getInstance(){
        if (instance == null){
            instance = new QuickSort();
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
    }

    public void sort(Object[] a, Comparator c) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1, c);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(Comparable[] a, int lo, int hi) { 
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
        assert isSorted(a, lo, hi);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(Object[] a, int lo, int hi, Comparator c) { 
        if (hi <= lo) return;
        int j = partition(a, lo, hi, c);
        sort(a, lo, j-1, c);
        sort(a, j+1, hi, c);
        assert isSorted(a, c, lo, hi);
    }

    // partition the subarray a[lo .. hi] by returning an index j
    // so that a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) { 

            // find item on lo to swap
            while (less(a[++i], v))
                if (i == hi) break;

            // find item on hi to swap
            while (less(v, a[--j]))
                if (j == lo) break;      // redundant since a[lo] acts as sentinel

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put v = a[j] into position
        exch(a, lo, j);

        // with a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    // partition the subarray a[lo .. hi] by returning an index j
    // so that a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
    private static int partition(Object[] a, int lo, int hi, Comparator c) {
        int i = lo;
        int j = hi + 1;
        Object v = a[lo];
        while (true) { 

            // find item on lo to swap
            while (less(c, a[++i], v))
                if (i == hi) break;

            // find item on hi to swap
            while (less(c, v, a[--j]))
                if (j == lo) break;      // redundant since a[lo] acts as sentinel

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put v = a[j] into position
        exch(a, lo, j);

        // with a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    /**
     * Rearranges the array so that a[k] contains the kth smallest key;
     * a[0] through a[k-1] are less than (or equal to) a[k]; and
     * a[k+1] through a[N-1] are greater than (or equal to) a[k].
     * @param a the array
     * @param k find the kth smallest
     */
    public static Comparable select(Comparable[] a, int k) {
        if (k < 0 || k >= a.length) {
            throw new IndexOutOfBoundsException("Selected element out of bounds");
        }
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi);
            if      (i > k) hi = i - 1;
            else if (i < k) lo = i + 1;
            else return a[i];
        }
        return a[lo];
    }

    /**
     * Rearranges the array so that a[k] contains the kth smallest key;
     * a[0] through a[k-1] are less than (or equal to) a[k]; and
     * a[k+1] through a[N-1] are greater than (or equal to) a[k].
     * @param a the array
     * @param k find the kth smallest
     */
    public static Object select(Object[] a, int k, Comparator c) {
        if (k < 0 || k >= a.length) {
            throw new IndexOutOfBoundsException("Selected element out of bounds");
        }
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi, c);
            if      (i > k) hi = i - 1;
            else if (i < k) lo = i + 1;
            else return a[i];
        }
        return a[lo];
    }

    /**
     * Reads in a sequence of strings from standard input; quicksorts them; 
     * and prints them to standard output in ascending order. 
     * Shuffles the array and then prints the strings again to
     * standard output, but this time, using the select method.
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        getInstance().sort(a);
        show(a);

        // shuffle
        StdRandom.shuffle(a);

        // display results again using select
        StdOut.println();
        for (int i = 0; i < a.length; i++) {
            String ith = (String) QuickSort.select(a, i);
            StdOut.println(ith);
        }
    }

}