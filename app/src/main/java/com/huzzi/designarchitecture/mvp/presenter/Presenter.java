package com.huzzi.designarchitecture.mvp.presenter;

import com.huzzi.designarchitecture.api.Api;
import com.huzzi.designarchitecture.mvc.model.Student;


public class Presenter {
    private View view;
    private Student myStudent;
    private Api api;
    public Presenter(Api api, View view){
        this.api =api;
        this.view=view;
    }

    public void fetchData(){
        view.showProgressBar(true);
        myStudent = api.getStudent();
        view.updateUserInfoTextView(myStudent!=null ? myStudent.getName(): null);
        view.showProgressBar(false);
    }

    public interface View{
        void updateUserInfoTextView(String info);
        void showProgressBar(boolean isShowing);
    }
}
