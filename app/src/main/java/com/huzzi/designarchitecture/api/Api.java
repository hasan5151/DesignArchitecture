package com.huzzi.designarchitecture.api;

import com.huzzi.designarchitecture.mvc.model.Student;

import io.reactivex.Observable;

public class Api {
    public Observable<Student> getStudentRx(){
        Student ourStudent = new Student();
        ourStudent.setName("hi");
        ourStudent.setId(31);
        return  Observable.just(ourStudent);
    }

    public Student getStudent(){
        Student student= new Student();
        student.setId(1);
        student.setName("huzzy");
        return  student;
    }
}
