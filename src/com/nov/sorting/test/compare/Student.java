package com.nov.sorting.test.compare;

import java.util.Comparator;

import static com.nov.sorting.test.compare.FormatOutput.*;

public class Student implements Comparable<Student> {

    private String name;
    private long ipn;
    private String group;
    private double averageMark;
    private int course;
    private String faculty;
    private String speciality;

    // порівнює студентів за податковим кодом
    public static final Comparator<Student> STUDENT_IPN = new SIpn();
    // порівнює студентів за курсом
    public static final Comparator<Student> STUDENT_COURSE = new SCourse();
    // порівнює студентів за середнім балом
    public static final Comparator<Student> STUDENT_MARK = new SAverageMark();
    // порівнює студентів за групою
    public static final Comparator<Student> STUDENT_GROUP = new SGroup();
    // порівнює студентів за факультетом
    public static final Comparator<Student> STUDENT_FACULTY = new SFaculty();
    // порівнює студентів за спеціальністю
    public static final Comparator<Student> STUDENT_SPECIALITY = new SSpeciality();

    // порівнює студентів за призвищем
    public int compareTo(Student other){
        return this.name.compareTo(other.name);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getIpn() {
        return ipn;
    }
    public void setIpn(long ipn) {
        this.ipn = ipn;
    }
    public String getFaculty() {
        return faculty;
    }
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    public String getSpeciality() {
        return speciality;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    public int getCourse() {
        return course;
    }
    public void setCourse(int course) {
        this.course = course;
    }
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    public double getAverageMark() {
        return averageMark;
    }
    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    public static class SIpn implements Comparator<Student>{
        public int compare(Student s1, Student s2){
            if (s1.ipn < s2.ipn) return -1;
            if (s1.ipn > s2.ipn) return  1;
            return 0;
        }
    }
    public static class SCourse implements Comparator<Student>{
        public int compare(Student s1, Student s2){
            if (s1.course < s2.course) return -1;
            if (s1.course > s2.course) return  1;
            return 0;
        }
    }
    public static class SAverageMark implements Comparator<Student>{
        public int compare(Student s1, Student s2){
            if (s1.averageMark < s2.averageMark) return -1;
            if (s1.averageMark > s2.averageMark) return  1;
            return 0;
        }
    }
    public static class SGroup implements Comparator<Student>{
        public int compare(Student s1, Student s2){
            return s1.group.compareTo(s2.group);
        }
    }
    public static class SFaculty implements Comparator<Student>{
        public int compare(Student s1, Student s2){
            return s1.faculty.compareTo(s2.faculty);
        }
    }
    public static class SSpeciality implements Comparator<Student>{
        public int compare(Student s1, Student s2){
            return s1.speciality.compareTo(s2.speciality);
        }
    }


    public static String heading() {

        return 	genLength("_",118) + "\n" +
                "Курс" +"|"+ "Група"+" |"+"Серед.бал"+"|"+"  І.П.Н.  |"+ fixLength("            П.І.Б.",30)+"|"+
                fixLength(" Найменування ФАКУЛЬТЕТУ",27)+"|"+fixLength("Найменування спеціальності",26)+"|"+"\n" +
                genLength("-",118);
    }

    public String toString() {
        return "".format("%3d", course)+" |"+ fixLength(group,6)+"|"+ "".format("%8.2f",averageMark)+" |"+"".format("%10d",ipn) +"|" +
                fixLength(name,30) + "|" + fixLength(faculty,27) +"|" +fixLength(speciality,26)+"|";
    }

}

