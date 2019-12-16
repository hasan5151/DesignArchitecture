package com.huzzi.designarchitecture;

import com.huzzi.designarchitecture.mvc.controller.StudentController;
import com.huzzi.designarchitecture.mvc.model.Student;
import com.huzzi.designarchitecture.mvc.view.MVCActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MVCTest {
     Student student;

    @Mock
    StudentController.View view;
    private StudentController studentController;
    @Before
    public void beforeTest(){
        MockitoAnnotations.initMocks(this);
        view = mock(MVCActivity.class);
        student = new Student();
        studentController = new StudentController(student,view);
    }

    @Test
    public void test(){
        studentController.setStudentName("hasan");
        studentController.setStudentId(1);

        studentController.updateUi();
        studentController.setStudentName("adas");
        studentController.updateUi();

     //   verify(view,times(2)).updateUi(student);
        verify(view,times(2)).updateUi(student);

        assertEquals(student.getName(),"adas");
        assertSame(student.getId(),1);
    }
}
