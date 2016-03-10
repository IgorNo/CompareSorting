package com.nov.sorting.algorithm.utils;

import java.util.Comparator;

import static com.nov.sorting.algorithm.utils.AuxiliarySortFunction.*;
import static com.nov.sorting.algorithm.utils.DebugSortFunction.*;

public class Merge {

    // This class should not be instantiated.
    private Merge() { }

    public static void merge(Comparable[] in1, Comparable[] in2, Comparable[] out) {
    // precondition:
    // in1 and in2 are sorted
    assert isSorted(in1);
    assert isSorted(in2);
    // is the length of out Array sufficient ?
    assert out.length == (in1.length + in2.length);

    int i = 0, j = 0, k = 0;
    while (i < in1.length && j < in2.length) {

        if (less(in1[i], in2[j]))
            out[k++] = in1[i++];
        else
            out[k++] = in2[j++];
//        System.out.println("k = "+k);
    }
    while (i < in1.length) {
        out[k++] = in1[i++];
    }
    while (j < in2.length) {
        out[k++] = in2[j++];
    }

    // postcondition: out is sorted
    assert isSorted(out);
}

}


