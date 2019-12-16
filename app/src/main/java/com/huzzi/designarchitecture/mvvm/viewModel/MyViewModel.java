package com.huzzi.designarchitecture.mvvm.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huzzi.designarchitecture.api.Api;
import com.huzzi.designarchitecture.api.ApiResponse;
import com.huzzi.designarchitecture.mvc.model.Student;

 import io.reactivex.disposables.CompositeDisposable;

public class MyViewModel extends ViewModel {
    private MutableLiveData<ApiResponse<Student>> student;
    private CompositeDisposable disposable;
    private Api api;

    public MyViewModel(Api api){
        disposable = new CompositeDisposable();
        student =new MutableLiveData<>();
        this.api= api;
    }
    public MutableLiveData<ApiResponse<Student>> getStudent() {
        return student;
    }

    public void fetchRxStudent(){
        disposable.add(api.getStudentRx().doOnSubscribe(disposable1 -> student.postValue(ApiResponse.LOADING_STATE)).subscribe(result->{
            ApiResponse.SUCCESS_STATE.setData(result);
            student.postValue(ApiResponse.SUCCESS_STATE);
        },throwable -> {
            ApiResponse.ERROR_STATE.setError(throwable);
            student.postValue(ApiResponse.ERROR_STATE);
        }));
    }
    public void fetchStudent(){
        ApiResponse.SUCCESS_STATE.setData(api.getStudent());
        student.postValue(ApiResponse.SUCCESS_STATE);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
