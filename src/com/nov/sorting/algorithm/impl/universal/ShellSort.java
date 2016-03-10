package com.nov.sorting.algorithm.impl.universal;

import com.nov.sorting.algorithm.interfaces.SortComparator;
import edu.princeton.cs.introcs.StdIn;

import java.util.Comparator;
import static com.nov.sorting.algorithm.utils.AuxiliarySortFunction.*;
import static com.nov.sorting.algorithm.utils.DebugSortFunction.*;

/*************************************************************************
 *  Compilation:  javac ShellSort.java
 *  Dependencies: StdOut.java StdIn.java
 *   
 *  The <tt>ShellSort</tt> class provides static methods for com.nov.sorting.comparator.sorting an
 *  array using Shellsort with Knuth's increment sequence (1, 4, 13, 40, ...).
 *
 *  Uses increment sequence proposed by Sedgewick and Incerpi.
 *  The nth element of the sequence is the smallest integer >= 2.5^n
 *  that is relatively prime to all previous terms in the sequence.
 *  For example, incs[4] is 41 because 2.5^4 = 39.0625 and 41 is
 *  the next integer that is relatively prime to 3, 7, and 16.
 *
 *************************************************************************/

public class ShellSort implements SortComparator{

    private static ShellSort instance;

    private ShellSort() { }

    public static ShellSort getInstance(){
        if (instance == null){
            instance = new ShellSort();
        }
        return instance;
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public void sort(Comparable[] a) {
        int N = a.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ... 
        int h = 1;
        while (h < N/3) h = 3*h + 1; 

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            assert isHsorted(a, h); 
            h /= 3;
        }
        assert isSorted(a);
    }

    public void sort(Object[] a, Comparator c) {
        int N = a.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ... 
        int h = 1;
        while (h < N/3) h = 3*h + 1; 

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(c,a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            assert isHsorted(a, h, c); 
            h /= 3;
        }
        assert isSorted(a, c);
    }



   /***********************************************************************
    *  Check if array is sorted - useful for debugging
    ***********************************************************************/
    // is the array h-sorted?
    private static boolean isHsorted(Comparable[] a, int h) {
        for (int i = h; i < a.length; i++)
            if (less(a[i], a[i-h])) return false;
        return true;
    }

    // is the array h-sorted?
    private static boolean isHsorted(Object[] a, int h, Comparator c) {
        for (int i = h; i < a.length; i++)
            if (less(c,a[i], a[i-h])) return false;
        return true;
    }

    /**
     * Reads in a sequence of strings from standard input; Shellsorts them; 
     * and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        getInstance().sort(a);
        show(a);
    }

}