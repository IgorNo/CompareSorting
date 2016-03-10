package com.nov.sorting.paralell.executors;

import com.nov.sorting.algorithm.interfaces.SortComparable;
import com.nov.sorting.algorithm.utils.Merge;
import com.nov.sorting.test.data.GenerateData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SortExecutor<T extends Comparable> {

    public T[] sort(GenerateData<T> data, SortComparable algorithm) throws InterruptedException, ExecutionException {

        int nummberOfThreads = data.getOddStepData().size();
        ExecutorService executor = Executors.newFixedThreadPool(nummberOfThreads);

        List<Callable<GenerateData<T>>> callables = new ArrayList<>();

        for (int i = 0; i < nummberOfThreads; i++) {
            callables.add(sort(data, i, algorithm));
        }

        executor.invokeAll(callables);

        List<T[]> evenList = data.getEvenStepData();
        List<T[]> oddList = data.getOddStepData();

        int nStep = lg(nummberOfThreads);
        for (int i = 0; i < nStep; i++) {
            callables.clear();
            if (i % 2 == 0){
                evenList.clear();
                for (int j = 0; j < (int)Math.pow(2, nStep-i); j+=2) {
                    int sizeOutArray = oddList.get(j).length + oddList.get(j+1).length;
                    evenList.add((T[]) Array.newInstance(data.getType(), sizeOutArray));
                    callables.add(mergeEven(data, j));
                }
            } else {
                oddList.clear();
                for (int j = 0; j < (int)Math.pow(2, nStep-i); j+=2) {
                    int sizeOutArray = evenList.get(j).length + evenList.get(j+1).length;
                    oddList.add((T[]) Array.newInstance(data.getType(), sizeOutArray));
                    callables.add(mergeOdd(data, j));
                }
            }
//            System.out.println("\ni = "+i);
            executor.invokeAll(callables);
        }
        executor.shutdown();


        if (nStep % 2 == 0)
            return oddList.get(0);
        else
            return evenList.get(0);

    }

    private Callable<GenerateData<T>> sort(GenerateData<T> data, int nThread, SortComparable algorithm){
        return () -> {
            algorithm.sort(data.getOddStepData().get(nThread));
            return data;
        };

    }

    private  Callable<GenerateData<T>> mergeEven(GenerateData<T> data, int n) {
        return () -> {
            Merge.merge(data.getOddStepData().get(n), data.getOddStepData().get(n+1), data.getEvenStepData().get(n/2));
            return data;
        };
    }

    private  Callable<GenerateData<T>> mergeOdd(GenerateData<T> data, int n) {
        return () -> {
            Merge.merge(data.getEvenStepData().get(n), data.getEvenStepData().get(n+1), data.getOddStepData().get(n/2));
            return data;
        };
    }

    public static int lg(int x) {
        return (int) (Math.log(x)/Math.log(2.0));
    }


}
