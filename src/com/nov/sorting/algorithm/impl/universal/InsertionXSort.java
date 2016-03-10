package com.nov.sorting.algorithm.impl.universal;

import com.nov.sorting.algorithm.interfaces.SortComparator;
import edu.princeton.cs.introcs.StdIn;

import java.util.Comparator;

import static com.nov.sorting.algorithm.utils.AuxiliarySortFunction.*;
import static com.nov.sorting.algorithm.utils.DebugSortFunction.*;

/*************************************************************************
 *  Compilation:  javac InsertionXSort.java
 *  Dependencies: StdOut.java StdIn.java
 *
 *  The <tt>InsertionXSort</tt> class provides static methods for com.nov.sorting.comparator.sorting an
 *  array using an optimized version of insertion sort (with half exchanges
 *  and a sentinel).
 *************************************************************************/

public class InsertionXSort implements SortComparator{

    private static InsertionXSort instance;

    private InsertionXSort() { }

    public static InsertionXSort getInstance(){
        if (instance == null){
            instance = new InsertionXSort();
        }
        return instance;
    }

    public void sort(Comparable[] a) {
        int N = a.length;

        // put smallest element in position to serve as sentinel
        for (int i = N-1; i > 0; i--)
            if (less(a[i], a[i-1])) exch(a, i, i-1);

        // insertion sort with half-exchanges
        for (int i = 2; i < N; i++) {
            Comparable v = a[i];
            int j = i;
            while (less(v, a[j-1])) {
                a[j] = a[j-1];
                j--;
            }
            a[j] = v;
        }

        assert isSorted(a);
    }

    public void sort(Object[] a, Comparator c) {
        int N = a.length;

        // put smallest element in position to serve as sentinel
        for (int i = N-1; i > 0; i--)
            if (less(c,a[i], a[i-1])) exch(a, i, i-1);

        // insertion sort with half-exchanges
        for (int i = 2; i < N; i++) {
            Comparable v = (Comparable) a[i];
            int j = i;
            while (less(c,v, a[j-1])) {
                a[j] = a[j-1];
                j--;
            }
            a[j] = v;
        }

        assert isSorted(a,c);
    }


    /**
     * Reads in a sequence of strings from standard input; insertion sorts them;
     * and prints them to standard output in ascending order.
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        getInstance().sort(a);
        show(a);
    }

}