package com.parkflash.project;

import android.test.ActivityInstrumentationTestCase2;

import java.util.concurrent.ExecutionException;

/**
 * Created by alban on 09/02/2018.
 */

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    static MainActivity mainActivity;

    public MainActivityTest(){
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception{
        super.setUp();
        mainActivity = getActivity();
    }


}
