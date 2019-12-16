package com.huzzi.designarchitecture;

import com.huzzi.designarchitecture.api.Api;
import com.huzzi.designarchitecture.mvc.model.Student;
import com.huzzi.designarchitecture.mvp.presenter.Presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MVPTest {
    @Mock
    Api api;
    @Mock
    Presenter.View view;
    private Presenter presenter;

    @Before
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
        presenter = new Presenter(api, view);
    }

    @Test
    public void test(){
        Student student = new Student();
        student.setId(3);
        student.setName("tr");
        when(api.getStudent()).thenReturn(student);
        presenter.fetchData();
        verify(view,times(1)).showProgressBar(true);
        verify(view,times(1)).showProgressBar(false);
        verify(view,times(1)).updateUserInfoTextView(student.getName());
        assertEquals(api.getStudent(),student);
    }

    @Test
    public void testNull(){
        when(api.getStudent()).thenReturn(null);
        presenter.fetchData();
        verify(view,times(1)).showProgressBar(true);
        verify(view,times(1)).showProgressBar(false);
        verify(view,times(1)).updateUserInfoTextView(null);
        assertEquals(api.getStudent(),null);
    }
}
