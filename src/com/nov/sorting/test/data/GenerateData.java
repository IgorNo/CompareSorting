package com.nov.sorting.test.data;

import com.nov.sorting.test.data.util.Generated;
import com.nov.sorting.test.data.util.RandomGenerator;
import com.nov.sorting.test.data.util.interfaces.Generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateData<T>  {

    T[] allData;

    private List<T[]> oddStepData = new ArrayList<>();

    private List<T[]> evenStepData = new ArrayList<>();

    private Class<T> type;

    public GenerateData(T[] dataArray, Generator<T> gen) {
        allData = Generated.array(dataArray, gen);
        this.type = (Class<T>) allData[0].getClass();
    }

    public GenerateData(Class<T> type, Generator<T> gen, int size) {
        allData = Generated.array(type, gen, size);
        this.type = type;
    }

    public Class<T> getType() {
        return type;
    }

    // nummberOfThreads = 1,2,4,8,16....2^n
    public void divideIntoParts(int nummberOfThreads){

        oddStepData.clear();
        evenStepData.clear();

        int nInOneThread = allData.length / nummberOfThreads;

        int nBegin = 0;
        for (int i = 0; i < nummberOfThreads-1; i++) {
            int nEnd = nBegin+nInOneThread;
            oddStepData.add(Arrays.copyOfRange(allData, nBegin, nEnd));
            nBegin = nEnd;
        }
        oddStepData.add(Arrays.copyOfRange(allData, nBegin, allData.length));
    }

    public List<T[]> getOddStepData() {
        return oddStepData;
    }

    public List<T[]> getEvenStepData() {
        return evenStepData;
    }
}
