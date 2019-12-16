package com.huzzi.designarchitecture.mvc.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.huzzi.designarchitecture.R;
import com.huzzi.designarchitecture.mvc.controller.StudentController;
import com.huzzi.designarchitecture.mvc.model.Student;

import org.w3c.dom.Text;

public class MVCActivity extends AppCompatActivity implements StudentController.View {
    TextView textView;
    StudentController studentController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView =findViewById(R.id.textView);
        Student student= getDataFromDB();
        studentController = new StudentController(student,this);

        studentController.updateUi();
        studentController.setStudentName("Merhaba");
        studentController.updateUi();

    }

    public Student getDataFromDB(){
        Student student= new Student();
        student.setId(12);
        student.setName("Hello");
        return student;
    }

    @Override
    public void updateUi(Student student) {
        textView.setText("Id:"+student.getId()+" Name:"+student.getName());
        System.out.println("MVC Test Id:"+student.getId()+" Name:"+student.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        studentController=null;
    }
}
