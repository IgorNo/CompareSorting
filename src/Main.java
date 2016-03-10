import com.nov.sorting.algorithm.interfaces.UniversalAlgorithm;
import com.nov.sorting.algorithm.utils.DebugSortFunction;
import com.nov.sorting.paralell.executors.SortExecutor;
import com.nov.sorting.test.data.GenerateData;
import com.nov.sorting.test.data.util.RandomGenerator;
import com.nov.sorting.test.data.util.interfaces.Generator;
import edu.princeton.cs.introcs.Out;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.Stopwatch;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import static edu.princeton.cs.introcs.StdOut.*;
import static edu.princeton.cs.introcs.StdIn.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Stopwatch time;
        double sortTime;
        Comparable[] sortArray;
        SortExecutor se = new SortExecutor();

        Out fileOut = new Out("Result Paralell Sorting Test"+ new Date().getTime()+".txt");
        int nType = 0;
        Class<?>[] typeData = RandomGenerator.class.getClasses();
        do {
            StdOut.println("Enter type of test data (0-exit): ");
            for (int i = 0; i < typeData.length; i++) {
                println("  " + (i + 1) + "." + typeData[i].getSimpleName());
            }
            print("Enter type of test data (0-exit): ");
            nType = readInt();

            if (nType>0 && nType <= typeData.length) {
                fileOut.println("Type of test data is "+typeData[nType - 1].getSimpleName());
                Generator<?> gen;
                Class c;
                try {
                    gen = (Generator<?>) typeData[nType - 1].newInstance();
                    c = Class.forName("java.lang." + typeData[nType - 1].getSimpleName());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                int sizeData = 0;
                do {
                    print("Enter size of data (0-exit): ");
                    sizeData = readInt();

                    if (sizeData > 0) {
                        fileOut.println("Size of test data = "+sizeData);
                        println("Start creating String test data sizeData = " + sizeData);
                        GenerateData data = new GenerateData(c, gen, sizeData);
                        println("End creating test data.");

                        int nThread = 0;
                        do {
                            print("Enter number of Thread (0-exit): ");
                            nThread = readInt();
                            if (nThread > 0) {
                                fileOut.println("Number of Thread = "+nThread);
                                int nAlgor = 0;
                                do {
                                    for (int i = 0; i < UniversalAlgorithm.TYPE.length; i++) {
                                        println("  " + (i + 1) + "." + UniversalAlgorithm.TYPE[i].getClass().getSimpleName());
                                    }
                                    print("Enter type of sort algorithm (0-exit): ");
                                    nAlgor = readInt();
                                    if (nAlgor>0 && nAlgor <= UniversalAlgorithm.TYPE.length) {
                                        String nameAlgor = UniversalAlgorithm.TYPE[nAlgor-1].getClass().getSimpleName();

                                        data.divideIntoParts(nThread);

                                        println("Start " + nameAlgor + " for nThread = " + nThread);
                                        time = new Stopwatch();
                                        sortArray = se.sort(data, UniversalAlgorithm.TYPE[nAlgor-1]);
                                        sortTime = time.elapsedTime();
                                        assert DebugSortFunction.isSorted(sortArray);
                                        println("End sort.");

                                        println("sizeData = " + sizeData + " number of Thread = " + nThread + " sortTime = " + sortTime);
                                        fileOut.println("For sort algorithm "+ nameAlgor + " sortTime = " + sortTime);
                                  }
                                } while (nAlgor>0);
                            }
                        } while (nThread > 0);
                    }
                } while (sizeData > 0);
            }
        } while (nType > 0);
        fileOut.close();
    }

}
