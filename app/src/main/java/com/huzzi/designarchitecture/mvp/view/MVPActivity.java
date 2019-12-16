package com.huzzi.designarchitecture.mvp.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.huzzi.designarchitecture.R;
import com.huzzi.designarchitecture.api.Api;
import com.huzzi.designarchitecture.mvp.presenter.Presenter;

public class MVPActivity  extends AppCompatActivity implements Presenter.View {
    Presenter presenter;
    TextView textView;
    Api api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        api = new Api();
        presenter = new Presenter(api,this);
        presenter.fetchData();
    }

    @Override
    public void updateUserInfoTextView(String info) {
        System.out.println("MVP Info"+info);
        textView.setText(info);
    }

    @Override
    public void showProgressBar(boolean isShowing) {
        System.out.println("MVP Progressbar"+isShowing);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter=null;
    }
}


