package com.nov.sorting.algorithm.interfaces;

import com.nov.sorting.algorithm.impl.string.*;
import com.nov.sorting.algorithm.impl.universal.*;

public interface StringAlgorithm {

    public static final SortComparable[] TYPE = { SelectionSort.getInstance(),
            InsertionSort.getInstance(), InsertionXSort.getInstance(), BinaryInsertionSort.getInstance(),
            MergeSort.getInstance(), MergeBUSort.getInstance(), MergeXSort.getInstance(),
            QuickSort.getInstance(), QuickXSort.getInstance(), Quick3waySort.getInstance(),
            ShellSort.getInstance(), HeapSort.getInstance(), JavaSort.getInstance(),
            StringLSDSort.getInstance(), StringMSDSort.getInstance(), StringQuick3waySort.getInstance()
    };

}
