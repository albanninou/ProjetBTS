package com.parkflash.project;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.lang.reflect.Method;
import java.util.UUID;

public class activity2 extends AppCompatActivity {
    BluetoothDevice device;
    BluetoothSocket tmp = null;
    BluetoothSocket mmSocket = null;
    BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String value = intent.getStringExtra(Bluetooth.BLUETOOTH_DEVICE); //if it's a string you stored.

        BluetoothManager mBluetoothManager = (BluetoothManager) getApplicationContext().getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();
        mBluetoothAdapter.cancelDiscovery();
        device = mBluetoothAdapter.getRemoteDevice(value);
        try {

            tmp = device.createRfcommSocketToServiceRecord(UUID.fromString(MainActivity.MY_UUID));
            Method m = device.getClass().getMethod("createRfcommSocket", new Class[]{int.class});
            tmp = (BluetoothSocket) m.invoke(device, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        mmSocket = tmp;
        setTitle(device.getName());
    }

}
