package com.parkflash.project;

import android.bluetooth.BluetoothDevice;

import junit.framework.TestCase;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by alban on 09/02/2018.
 */

public class BluetoothTest extends TestCase {

    MainActivity mainActivity;
    Bluetooth bluetooth;

    @Override
    protected void setUp() throws Exception{
        super.setUp();

        bluetooth = new Bluetooth(MainActivityTest.mainActivity);
    }


    @Test
    public void testpow() {
        int result = bluetooth.pow(5);
        assertEquals(25,result);
    }
}
