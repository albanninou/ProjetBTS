package com.parkflash.flashled;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by alban on 05/03/2018.
 */

    @RunWith(AndroidJUnit4.class)
    public class MainActivityTest {
        @Rule
        public ActivityTestRule<MainActivity> mActivityRule
                = new ActivityTestRule<>(MainActivity.class);

        @Test
        public void Noyau() throws Exception {
            assertEquals(4, 2 + 2);
        }
    }

