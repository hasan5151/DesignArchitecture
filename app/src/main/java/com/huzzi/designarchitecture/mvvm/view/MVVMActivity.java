package com.huzzi.designarchitecture.mvvm.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.huzzi.designarchitecture.R;
import com.huzzi.designarchitecture.api.Api;
import com.huzzi.designarchitecture.mvvm.viewModel.MyViewModel;
import com.huzzi.designarchitecture.mvvm.viewModel.MyViewModelFactory;

public class MVVMActivity extends AppCompatActivity {
    MyViewModel myViewModel;
    public TextView textView;
    MyViewModelFactory myViewModelFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        myViewModelFactory = new MyViewModelFactory(new Api());
        myViewModel = ViewModelProviders.of(this,myViewModelFactory).get(MyViewModel.class);

        myViewModel.fetchRxStudent();
        myViewModel.getStudent().observe(this,result->{
                    switch (result.getStatus()){
                        case ERROR:
                            System.out.println("MVVM Result Error");
                        case LOADING:
                            System.out.println("MVVM Result Loading");
                        case SUCCESS:
                            System.out.println("MVVM Result"+result.getData().getName());
                            textView.setText(result.getData().getName());
                    }
        });
    }
}
