package com.parkflash.project.Commun;

import android.test.ActivityInstrumentationTestCase2;

import com.parkflash.project.ColorFonctionnement;
import com.parkflash.project.MainActivity;
import com.parkflash.project.ModeFonctionnement;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Created by alban on 13/02/2018.
 */

public class NoyauTest  extends ActivityInstrumentationTestCase2<MainActivity> {

    MainActivity mainActivity;

    public NoyauTest() {
        super("com.parkflash.project", MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
         mainActivity = getActivity();
    }

    @Test
    public void createNoyauTest() {
        ColorFonctionnement colorFonctionnement = new ColorFonctionnement(mainActivity);
        colorFonctionnement.setColorFonctionnement(1);
        ModeFonctionnement modeFonctionnement = new ModeFonctionnement(mainActivity);
        modeFonctionnement.setFonctionnementMode(2);
        Noyau noyau = new Noyau(mainActivity,mainActivity,colorFonctionnement,modeFonctionnement,6);
        assertEquals(6,noyau.nbPlaque);
        assertEquals(6,noyau.getPlaques().size());
        assertEquals(1,noyau.getColorFonctionnement().getColorFonctionnement());
        assertEquals(2,noyau.getModeFonctionnement().getFonctionnementMode());
    }
}
