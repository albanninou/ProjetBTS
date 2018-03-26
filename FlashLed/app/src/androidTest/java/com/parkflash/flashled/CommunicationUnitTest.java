package com.parkflash.flashled;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by alban on 23/03/2018.
 */
@RunWith(AndroidJUnit4.class)
public class CommunicationUnitTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void Communication() throws Exception {
        MainActivity mainActivity = mActivityRule.getActivity();
        Noyau noyau= new Noyau(mainActivity,mainActivity.getApplicationContext(),new ColorFonctionnement(mainActivity),new ModeFonctionnement(mainActivity),4);
        Communication communication = new Communication(noyau,null);
        assertEquals(communication.noyau,noyau);
    }
}
