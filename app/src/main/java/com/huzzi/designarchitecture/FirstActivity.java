package com.huzzi.designarchitecture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.huzzi.designarchitecture.mvc.view.MVCActivity;
import com.huzzi.designarchitecture.mvp.view.MVPActivity;
import com.huzzi.designarchitecture.mvvm.view.MVVMActivity;

public class FirstActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
    }

    public void onClick(View view) {
        Button button = (Button) view;
        if(button.getText().toString().contentEquals("MVC")){
            startActivity(new Intent(this, MVCActivity.class));
        }else if(button.getText().toString().contentEquals("MVP")){
            startActivity(new Intent(this, MVPActivity.class));
        }else if(button.getText().toString().contentEquals("MVVM")){
            startActivity(new Intent(this, MVVMActivity.class));
        }
    }
}
