package com.nov.sorting.algorithm.impl.universal;

import com.nov.sorting.algorithm.interfaces.SortComparable;
import com.nov.sorting.algorithm.interfaces.SortComparator;
import edu.princeton.cs.introcs.StdIn;

import java.util.Comparator;

import static com.nov.sorting.algorithm.utils.AuxiliarySortFunction.*;
import static com.nov.sorting.algorithm.utils.DebugSortFunction.*;
/**
 *  The <tt>BinaryInsertionSort</tt> class provides a static method for sorting an
 *  array using an optimized binary insertion sort with half exchanges.
 *  <p>
 *  This implementation makes ~ N lg N compares for any array of length N.
 *  However, in the worst case, the running time is quadratic because the
 *  number of array accesses can be proportional to N^2 (e.g, if the array
 *  is reverse sorted). As such, it is not suitable for sorting large
 *  arrays (unless the number of inversions is small).
 *  <p>
 *  The sorting algorithm is stable and uses O(1) extra memory.
 *  <p>
 */
public class BinaryInsertionSort implements SortComparator{

    private static BinaryInsertionSort instance;

    private BinaryInsertionSort() { }

    public static BinaryInsertionSort getInstance(){
        if (instance == null){
            instance = new BinaryInsertionSort();
        }
        return instance;
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {

            // binary search to determine index j at which to insert a[i]
            Comparable v = a[i];
            int lo = 0, hi = i;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (less(v, a[mid])) hi = mid;
                else                 lo = mid + 1;
            }

            // insetion sort with "half exchanges"
            // (insert a[i] at index j and shift a[j], ..., a[i-1] to right)
            for (int j = i; j > lo; --j)
                a[j] = a[j-1];
            a[lo] = v;
        }
        assert isSorted(a);
    }

    // use a custom order and Comparator interface
    public void sort(Object[] a, Comparator c) {
        int N = a.length;
        for (int i = 1; i < N; i++) {

            // binary search to determine index j at which to insert a[i]
            Object v = a[i];
            int lo = 0, hi = i;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (less(c, v, a[mid])) hi = mid;
                else                 lo = mid + 1;
            }

            // insetion sort with "half exchanges"
            // (insert a[i] at index j and shift a[j], ..., a[i-1] to right)
            for (int j = i; j > lo; --j)
                a[j] = a[j-1];
            a[lo] = v;
        }
        assert isSorted(a, c);
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

