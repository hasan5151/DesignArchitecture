package com.huzzi.designarchitecture.mvc.controller;

import com.huzzi.designarchitecture.mvc.model.Student;

public class StudentController {
    private Student student;
    private View view;
    public StudentController(Student student, View view){
        this.student=student;
        this.view=view;
    }

    public void setStudentName(String name){
        student.setName(name);
    }

    public void setStudentId(Integer id){
        student.setId(id);
    }

    public String getStudentName(){
        return student.getName();
    }

    public Integer getStudentId(){
        return student.getId();
    }

    public void updateUi(){
        view.updateUi(student);
    }

    public interface View{
        void updateUi(Student student);
    }
}
