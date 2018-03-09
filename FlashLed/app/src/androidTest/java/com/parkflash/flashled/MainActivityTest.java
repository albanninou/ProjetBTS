package com.parkflash.flashled;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by alban on 05/03/2018.
 */

public class MainActivityTest {
    @RunWith(AndroidJUnit4.class)
    public class MainActivityTests {
        @Rule
        public ActivityTestRule<MainActivity> mActivityRule
                = new ActivityTestRule<>(MainActivity.class);


    }
}
