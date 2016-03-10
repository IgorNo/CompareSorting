package com.nov.sorting.algorithm.impl.string;

/******************************************************************************
 *  Compilation:  javac StringLSDSort.java
 *  Dependencies: StdIn.java StdOut.java
 *
 *  StringLSDSort radix sort
 *
 *    - Sort a String[] array of N extended ASCII strings (R = 256), each of length W.
 *
 *    - Sort an int[] array of N 32-bit integers, treating each integer as
 *      a sequence of W = 4 bytes (R = 256).
 *
 *  Uses extra space proportional to N + R.
 *
 ******************************************************************************/


import com.nov.sorting.algorithm.interfaces.SortComparable;
import edu.princeton.cs.introcs.StdIn;

import static com.nov.sorting.algorithm.utils.DebugSortFunction.*;

/**
 *  The <tt>StringLSDSort</tt> class provides methods for sorting an
 *  array of Fixed-size strings LSD (least signification digit first) radix sort.
 *  <p>
 */
public class StringLSDSort implements SortComparable<String> {

    private static StringLSDSort instance;

    private StringLSDSort() { }

    public static StringLSDSort getInstance(){
        if (instance == null){
            instance = new StringLSDSort();
        }
        return instance;
    }

    /**
     * Rearranges the array of Fixed-size strings in ascending order.
     *
     * @param a the array to be sorted
     */
    public void sort(String[] a) {

        assert isFixedLengh(a): "Strings must have fixed length";

        int N = a.length;
        int W = a[0].length();
        int R = 256;   // extend ASCII alphabet size
        String[] aux = new String[N];

        for (int d = W-1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R+1];
            for (int i = 0; i < N; i++)
                count[a[i].charAt(d) + 1]++;

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            // move data
            for (int i = 0; i < N; i++)
                aux[count[a[i].charAt(d)]++] = a[i];

            // copy back
            for (int i = 0; i < N; i++)
                a[i] = aux[i];
        }

        assert isSorted(a);
    }

    // check that strings have fixed length
    private static boolean isFixedLengh(String[] a){
        int N = a.length;
        int W = a[0].length();

        for (int i = 1; i < N; i++)
            if (a[i].length() != W)
                return false;

        return true;
    }

    /**
     * Reads in a sequence of fixed-length strings from standard input;
     * StringLSDSort radix sorts them;
     * and prints them to standard output in ascending order.
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        // sort the strings
        getInstance().sort(a);
        show(a);
    }
}

