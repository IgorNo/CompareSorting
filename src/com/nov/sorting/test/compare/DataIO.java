package com.nov.sorting.test.compare;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;

import java.io.IOException;

import static com.nov.sorting.test.compare.FormatOutput.genLength;

public class DataIO {

	public static Student[] readData (String fileName) throws IOException{

		In in = new In(fileName);
		int N = in.readInt();
		Student[] sArr = new Student[N];
		for (int i=0; i<4; i++) in.readLine();
		for (int i=0; i<N; i++) {
			String line = in.readLine();
			String[] tokens = line.split("\\|");
			sArr[i] = new Student();
			sArr[i].setCourse(Integer.parseInt(tokens[0].trim()));
			sArr[i].setGroup(tokens[1].trim());
			sArr[i].setAverageMark(Double.parseDouble(tokens[2].trim()));
			sArr[i].setIpn(Long.parseLong(tokens[3].trim()));
			sArr[i].setName(tokens[4].trim());
			sArr[i].setFaculty(tokens[5].trim());
			sArr[i].setSpeciality(tokens[6].trim());		
		}
		in.close();
//		System.out.println(Student.heading());
//		for (Student s:sArr) System.out.println(s); 	
		return sArr;
	}
	
public static void writeData (Out out, Student[] sArr) throws IOException{

	out.println(Student.heading());
	for (Student s:sArr) out.println(s); 
	out.println(genLength("-",118));
	out.println();
}


}
