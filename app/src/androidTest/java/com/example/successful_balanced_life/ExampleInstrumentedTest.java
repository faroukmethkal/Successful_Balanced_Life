package com.example.successful_balanced_life;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.successful_balanced_life.model.Goal;
import com.example.successful_balanced_life.viewmodel.GoalViewModel;

import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private GoalViewModel viewModel;


    @Test
    public void getGoal() {

        viewModel = new GoalViewModel(getApplicationContext());

    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.successful_balanced_life", appContext.getPackageName());
    }
}