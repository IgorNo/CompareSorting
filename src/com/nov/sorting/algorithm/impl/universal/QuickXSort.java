package com.nov.sorting.algorithm.impl.universal;

import com.nov.sorting.algorithm.interfaces.SortComparator;
import edu.princeton.cs.introcs.StdIn;

import java.util.Comparator;
import static com.nov.sorting.algorithm.utils.AuxiliarySortFunction.*;
import static com.nov.sorting.algorithm.utils.DebugSortFunction.*;
import static com.nov.sorting.algorithm.impl.universal.InsertionSort.*;


/*************************************************************************
 *  Compilation:  javac QuickXSort.java
 *  
 *  The <tt>QuickXSort</tt> class provides static methods for com.nov.sorting.comparator.sorting an
 *  array using an optimized version of quicksort.
 *
 *  Uses the Bentley-McIlroy 3-way partitioning scheme,
 *  chooses the partitioning element using Tukey's ninther,
 *  and cuts off to insertion sort.
 *
 *  Reference: Engineering a Sort Function by Jon L. Bentley
 *  and M. Douglas McIlroy. Softwae-Practice and Experience,
 *  Vol. 23 (11), 1249-1265 (November 1993).
 *
 *************************************************************************/

public class QuickXSort implements SortComparator{
    private static final int CUTOFF = 8;  // cutoff to insertion sort, must be >= 1

    private static QuickXSort instance;

    private QuickXSort() { }

    public static QuickXSort getInstance(){
        if (instance == null){
            instance = new QuickXSort();
        }
        return instance;
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    public void sort(Object[] a, Comparator c) {
        sort(a, 0, a.length - 1, c);
    }

    private static void sort(Object[] a, int lo, int hi, Comparator c) { 
        int N = hi - lo + 1;

        // cutoff to insertion sort
        if (N <= CUTOFF) {
            insertionSort(a, lo, hi, c);
            return;
        }

        // use median-of-3 as partitioning element
        else if (N <= 40) {
            int m = median3(a, lo, lo + N/2, hi, c);
            exch(a, m, lo);
        }

        // use Tukey ninther as partitioning element
        else  {
            int eps = N/8;
            int mid = lo + N/2;
            int m1 = median3(a, lo, lo + eps, lo + eps + eps, c);
            int m2 = median3(a, mid - eps, mid, mid + eps, c);
            int m3 = median3(a, hi - eps - eps, hi - eps, hi, c); 
            int ninther = median3(a, m1, m2, m3, c);
            exch(a, ninther, lo);
        }

        // Bentley-McIlroy 3-way partitioning
        int i = lo, j = hi+1;
        int p = lo, q = hi+1;
        while (true) {
        	Object v = a[lo];
            while (less(c, a[++i], v))
                if (i == hi) break;
            while (less(c, v, a[--j]))
                if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
            if (eq(c, a[i], v)) exch(a, ++p, i);
            if (eq(c, a[j], v)) exch(a, --q, j);
        }
        exch(a, lo, j);

        i = j + 1;
        j = j - 1;
        for (int k = lo+1; k <= p; k++) exch(a, k, j--);
        for (int k = hi  ; k >= q; k--) exch(a, k, i++);

        sort(a, lo, j, c);
        sort(a, i, hi, c);
    }

    private static void sort(Comparable[] a, int lo, int hi) { 
        int N = hi - lo + 1;

        // cutoff to insertion sort
        if (N <= CUTOFF) {
            insertionSort(a, lo, hi);
            return;
        }

        // use median-of-3 as partitioning element
        else if (N <= 40) {
            int m = median3(a, lo, lo + N/2, hi);
            exch(a, m, lo);
        }

        // use Tukey ninther as partitioning element
        else  {
            int eps = N/8;
            int mid = lo + N/2;
            int m1 = median3(a, lo, lo + eps, lo + eps + eps);
            int m2 = median3(a, mid - eps, mid, mid + eps);
            int m3 = median3(a, hi - eps - eps, hi - eps, hi); 
            int ninther = median3(a, m1, m2, m3);
            exch(a, ninther, lo);
        }

        // Bentley-McIlroy 3-way partitioning
        int i = lo, j = hi+1;
        int p = lo, q = hi+1;
        while (true) {
            Comparable v = a[lo];
            while (less(a[++i], v))
                if (i == hi) break;
            while (less(v, a[--j]))
                if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
            if (eq(a[i], v)) exch(a, ++p, i);
            if (eq(a[j], v)) exch(a, --q, j);
        }
        exch(a, lo, j);

        i = j + 1;
        j = j - 1;
        for (int k = lo+1; k <= p; k++) exch(a, k, j--);
        for (int k = hi  ; k >= q; k--) exch(a, k, i++);

        sort(a, lo, j);
        sort(a, i, hi);
    }



    // return the index of the median element among a[i], a[j], and a[k]
    private static int median3(Comparable[] a, int i, int j, int k) {
        return (less(a[i], a[j]) ?
               (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i) :
               (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
    }

    // return the index of the median element among a[i], a[j], and a[k]
    private static int median3(Object[] a, int i, int j, int k, Comparator c) {
        return (less(c, a[i], a[j]) ?
               (less(c, a[j], a[k]) ? j : less(c, a[i], a[k]) ? k : i) :
               (less(c, a[k], a[j]) ? j : less(c, a[k], a[i]) ? k : i));
    }

    /**
     * Reads in a sequence of strings from standard input; quicksorts them
     * (using an optimized version of quicksort); 
     * and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        getInstance().sort(a);
        show(a);
    }

}