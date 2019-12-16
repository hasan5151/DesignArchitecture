package com.huzzi.designarchitecture;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.huzzi.designarchitecture.api.Api;
import com.huzzi.designarchitecture.api.ApiResponse;
import com.huzzi.designarchitecture.mvc.model.Student;
import com.huzzi.designarchitecture.mvvm.view.MVVMActivity;
import com.huzzi.designarchitecture.mvvm.viewModel.MyViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;

import io.reactivex.Observable;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
@Config(sdk=28)
public class MVVMTest {

    @Rule
    public ActivityTestRule<MVVMActivity> mActivityRule =
            new ActivityTestRule<>(MVVMActivity.class);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    MyViewModel myViewModel;

    @Mock
    Api api;

    @Mock
    Observer<ApiResponse> observer;

    @Before
    public void beforeTest(){
        MockitoAnnotations.initMocks(this);
        myViewModel = new MyViewModel(api);
        verify(observer).onChanged(ApiResponse.ERROR_STATE);
        myViewModel.getStudent().observeForever(observer);
    }

    @Test
    public void testNull(){
        when(api.getStudentRx()).thenReturn(null);
        assertNotNull(myViewModel.getStudent());
        assertTrue(myViewModel.getStudent().hasObservers());
    }

    @Test
    public void testWithValueRx(){
        Student ourStudent =new Student();
        ourStudent.setId(112);
        ourStudent.setName("This is MVVM");
        when(api.getStudentRx()).thenReturn(Observable.just(ourStudent));
        myViewModel.fetchRxStudent();
        verify(observer).onChanged(ApiResponse.SUCCESS_STATE);
        api.getStudentRx().test().assertResult(ourStudent);
    }

    @Test
    public void fetchStudentTest(){
        Student ourStudent =new Student();
        ourStudent.setId(112);
        ourStudent.setName("This is MVVM");
        when(api.getStudent()).thenReturn(ourStudent);
        myViewModel.fetchStudent();
        verify(observer).onChanged(ApiResponse.SUCCESS_STATE);
        assertEquals(ApiResponse.SUCCESS_STATE.getData(),ourStudent);
    }

    @Test
    public void fetchStudentTestWithError(){
        Exception exception = new Exception();
        when(api.getStudentRx()).thenReturn(Observable.error(exception));
        myViewModel.fetchRxStudent();
        verify(observer).onChanged(ApiResponse.ERROR_STATE);
        assertEquals(ApiResponse.ERROR_STATE.getError(),exception);
    }

    @Test
    public void testWithRobo(){
        System.out.println(mActivityRule.getActivity().textView.getText());
        assertEquals(mActivityRule.getActivity().textView.getText(),"hi");
    }

    @After
    public void tearDown() {
        api = null;
        myViewModel = null;
    }
}
