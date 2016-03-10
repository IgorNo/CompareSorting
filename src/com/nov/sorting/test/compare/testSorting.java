package com.nov.sorting.test.compare;

import com.nov.sorting.algorithm.interfaces.SortComparator;
import com.nov.sorting.algorithm.interfaces.UniversalAlgorithm;
import edu.princeton.cs.introcs.Out;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.Stopwatch;

import java.io.IOException;

import static com.nov.sorting.algorithm.utils.DebugSortFunction.isSorted;
import static com.nov.sorting.test.compare.DataIO.readData;
import static com.nov.sorting.test.compare.DataIO.writeData;

public class testSorting {

	private class Result {
					
		private boolean isStability;
		private double time;			
	}
	
	private static boolean isStability(Student[] stArr){
		
		int lo = 0, hi = 0;
		for (int i=1; i<stArr.length; i++) {
			hi = i;
			if (stArr[lo].getCourse()!=stArr[hi].getCourse()) {
				if ( !isSorted(stArr, lo, hi-1)){
					return false;
				}
				lo = hi;
			}
		}
		if ( !isSorted(stArr, lo, hi-1)){
			return false;
		}
		return true;
	}
	
	private static Result testSort(Student[] stArr, SortComparator sortAlgorithm) throws IOException{
		
		testSorting x = new testSorting();
		Result result = x.new Result();
		StdRandom.shuffle(stArr);
		Out stdOut = new Out();
		Out fileOut = new Out("Result Test "+sortAlgorithm.getClass().getSimpleName()+"-UTF8.txt");
		StdOut.println("Тест алгоритму "+sortAlgorithm.getClass().getName());
		fileOut.println("Тест алгоритму "+sortAlgorithm.getClass().getName());
		StdOut.println("Массив після випадкового перемішування:");
		fileOut.println("Массив після випадкового перемішування:");
		writeData(stdOut,stArr);
		writeData(fileOut,stArr);



		Stopwatch time = new Stopwatch();
		sortAlgorithm.sort(stArr,Student.STUDENT_MARK);
		result.time += time.elapsedTime();

		StdOut.println("Массив після сортування за середнім балом:");
		fileOut.println("Массив після сортування за середнім балом:");
		writeData(stdOut,stArr);
		writeData(fileOut,stArr);

		time = new Stopwatch();
		sortAlgorithm.sort(stArr);
		result.time += time.elapsedTime();

		StdOut.println("Массив після сортування за призвищем:");
		fileOut.println("Массив після сортування за призвищем:");
		writeData(stdOut,stArr);
		writeData(fileOut,stArr);

		time = new Stopwatch();
		sortAlgorithm.sort(stArr,Student.STUDENT_COURSE);
		result.time += time.elapsedTime();

		StdOut.println("Массив після сортування за курсом:");
		fileOut.println("Массив після сортування за курсом:");
		writeData(stdOut,stArr);
		writeData(fileOut,stArr);
		
		StdOut.println("РЕЗУЛЬТАТИ ТЕСТУВАННЯ:");
		fileOut.println("РЕЗУЛЬТАТИ ТЕСТУВАННЯ:");		
		if (isStability(stArr)) {
			StdOut.println("АЛГОРИТМ "+sortAlgorithm.getClass().getName()+" є СТАБІЛЬНИМ");
			fileOut.println("АЛГОРИТМ "+sortAlgorithm.getClass().getName()+" є СТАБІЛЬНИМ");
			result.isStability = true;
		} else {
			StdOut.println("АЛГОРИТМ "+sortAlgorithm.getClass().getName()+" є НЕСТАБІЛЬНИМ");
			fileOut.println("АЛГОРИТМ "+sortAlgorithm.getClass().getName()+" є НЕСТАБІЛЬНИМ");
			result.isStability = false;
		}	
		StdOut.println("ЧАС ВИКОНАННЯ СОРТУВАННЯ: "+result.time+"с");
		fileOut.println("ЧАС ВИКОНАННЯ СОРТУВАННЯ: "+result.time+"с");		
		fileOut.close();
		return result;
	}

	
	public static void main(String[] args) throws IOException{
		
		Student[] students = readData("student-UTF8.txt");
		
		Out fileOut = new Out("Result Test All Algoritm-UTF8.txt");
		StdOut.println("ЗАГАЛЬНІ РЕЗУЛЬТАТИ ТЕСТУВАННЯ:");
		fileOut.println("ЗАГАЛЬНІ РЕЗУЛЬТАТИ ТЕСТУВАННЯ:");	

		for (SortComparator x: UniversalAlgorithm.TYPE) {
			Result result = testSort(students,x);
			if (result.isStability) {
				StdOut.println("АЛГОРИТМ "+x.getClass().getName()+" є СТАБІЛЬНИМ");
				fileOut.println("АЛГОРИТМ "+x.getClass().getName()+" є СТАБІЛЬНИМ");
				result.isStability = true;
			} else {
				StdOut.println("АЛГОРИТМ "+x.getClass().getName()+" є НЕСТАБІЛЬНИМ");
				fileOut.println("АЛГОРИТМ "+x.getClass().getName()+" є НЕСТАБІЛЬНИМ");
				result.isStability = false;
			}	
			StdOut.println("ЧАС ВИКОНАННЯ СОРТУВАННЯ: "+result.time+"с");
			fileOut.println("ЧАС ВИКОНАННЯ СОРТУВАННЯ: "+result.time+"с");					
		}
		fileOut.close();		
	}

}
