package com.nov.sorting.algorithm.interfaces;

import com.nov.sorting.algorithm.impl.universal.*;

public interface UniversalAlgorithm {

    public static final SortComparator[] TYPE = {SelectionSort.getInstance(),
            InsertionSort.getInstance(), InsertionXSort.getInstance(), BinaryInsertionSort.getInstance(),
            MergeSort.getInstance(), MergeBUSort.getInstance(), MergeXSort.getInstance(),
            QuickSort.getInstance(), QuickXSort.getInstance(), Quick3waySort.getInstance(),
            ShellSort.getInstance(), HeapSort.getInstance(), JavaSort.getInstance()
    };

}
