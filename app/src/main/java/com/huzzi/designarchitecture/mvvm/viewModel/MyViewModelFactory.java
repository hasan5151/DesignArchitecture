package com.huzzi.designarchitecture.mvvm.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.huzzi.designarchitecture.api.Api;

public class MyViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Api api;

    public MyViewModelFactory(Api api){
        this.api=api;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MyViewModel(api);
    }
}
