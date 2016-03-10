package com.nov.sorting.test.paralell;

import com.nov.sorting.algorithm.interfaces.StringAlgorithm;
import com.nov.sorting.algorithm.interfaces.UniversalAlgorithm;
import com.nov.sorting.algorithm.utils.DebugSortFunction;
import com.nov.sorting.paralell.executors.SortExecutor;
import com.nov.sorting.test.data.GenerateData;
import com.nov.sorting.test.data.util.RandomGenerator;
import edu.princeton.cs.introcs.Out;
import edu.princeton.cs.introcs.Stopwatch;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import static edu.princeton.cs.introcs.StdOut.println;

public class StringTest {

    public static final int[] SIZE_DATA = {10000, 100000, 500000, 1000000};
    public static final int N_THREAD_MAX = 32;

    public static void test(int lengthSting) throws InterruptedException, ExecutionException {

        Stopwatch time;
        double sortTime;
        Comparable[] sortArray;
        SortExecutor se = new SortExecutor();

        Out fileOut = new Out("Result Paralell Sorting Test for String Data"+ new Date().getTime()+".txt");
        fileOut.println("String length = " + lengthSting);
        for (int i = 0; i < SIZE_DATA.length; i++) {
            fileOut.println("Size of String test data = " + SIZE_DATA[i]);
            println("Start creating String test data sizeData = " + SIZE_DATA[i]);
            GenerateData data = new GenerateData(String.class ,new RandomGenerator.String(lengthSting), SIZE_DATA[i]);
            println("End creating test data.");
            for (int nThread = 1; nThread <= N_THREAD_MAX ; nThread*=2) {
                fileOut.println("Number of Thread = "+nThread);

                for (int j = 0; j < StringAlgorithm.TYPE.length; j++) {
                    data.divideIntoParts(nThread);
                    String nameAlgor = StringAlgorithm.TYPE[j].getClass().getSimpleName();
                    println("Start " + nameAlgor + " for nThread = " + nThread);
                    time = new Stopwatch();
                    sortArray = se.sort(data, StringAlgorithm.TYPE[j]);
                    sortTime = time.elapsedTime();
                    assert DebugSortFunction.isSorted(sortArray);
                    println("End sort.");

                    println("sizeData = " + SIZE_DATA[i] + " number of Thread = " + nThread + " sortTime = " + sortTime);
                    fileOut.println("For sort algorithm "+ nameAlgor + " sortTime = " + sortTime);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException{

//        test(10);

        test(100);

    }
}
