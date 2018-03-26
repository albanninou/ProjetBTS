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
public class NoyauUnitTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule
            = new ActivityTestRule<>(MainActivity.class);


    int nbPlaque = 4;


    @Test
    public void getList() throws Exception {
        MainActivity mainActivity = mActivityRule.getActivity();
        Noyau noyau= new Noyau(mainActivity,mainActivity.getApplicationContext(),new ColorFonctionnement(mainActivity),new ModeFonctionnement(mainActivity),nbPlaque);
        assertEquals(noyau.getPlaques().size(),nbPlaque);
    }

    @Test
    public void Noyau() throws Exception {
        MainActivity mainActivity = mActivityRule.getActivity();
        Noyau noyau= new Noyau(mainActivity,mainActivity.getApplicationContext(),new ColorFonctionnement(mainActivity),new ModeFonctionnement(mainActivity),nbPlaque);
        assertEquals(noyau.nbPlaque,nbPlaque);
    }
}