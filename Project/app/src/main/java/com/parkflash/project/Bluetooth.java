package com.parkflash.project;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by alban on 16/01/2018.
 */

public class Bluetooth {
    MainActivity mainActivity;
    BluetoothAdapter mBluetoothAdapter;
    HashMap<String, BluetoothDevice> bluetoothDeviceMap;

    public static String BLUETOOTH_DEVICE = "device";

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView av, View v, int arg2, long arg3) {
            // Get the device MAC address, the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);
            Intent i = new Intent(mainActivity, activity2.class);
            i.putExtra(BLUETOOTH_DEVICE, address); //this will be received at ledControl (class) Activity
            i.putExtra(ColorFonctionnement.COLOR, mainActivity.colorFonctionnement.getColorFonctionnement());
            i.putExtra(ModeFonctionnement.MODE, mainActivity.modeFonctionnement.getFonctionnementMode());
            mainActivity.startActivity(i);
        }
    };
    // Create a BroadcastReceiver for ACTION_FOUND.
    public final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                //discovery starts, we can show progress dialog or perform other tasks
                Log.e("parkflash", "start realy scan");
            }
            if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                //discovery finishes, dismis progress dialog
                Log.e("parkflash", "scan finish");
            }
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //bluetooth device found
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                bluetoothDeviceMap.put(device.getName(), device);
                Log.e("parkflash", "Found device " + device.getName());
                pairedDevicesList();
            }
        }
    };

    public Bluetooth(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        BluetoothManager mBluetoothManager = (BluetoothManager) mainActivity.getApplicationContext().getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();
        bluetoothDeviceMap = new HashMap<String, BluetoothDevice>();
        if (mBluetoothAdapter == null) {
            Log.e("ParkFlash", "telephone compatible bluetooth");
        } else {
            Log.e("ParkFlash", "telephone non compatible bluetooth");
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Log.e("ParkFlash", "activation du bluetooth");
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            mainActivity.startActivityForResult(enableBtIntent, MainActivity.REQUEST_ENABLE_BLUETOOTH_BT);
        } else {
            Log.e("ParkFlash", "bluethoot deja activer");
            startScan();
        }
    }

    public void startScan() {
        Log.e("parkflash", "start scan");
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        mainActivity.registerReceiver(mReceiver, filter);
        mBluetoothAdapter.startDiscovery();
    }

    public void pairedDevicesList() {
        ArrayList list = new ArrayList();
        if (bluetoothDeviceMap.size() > 0) {
            for (BluetoothDevice bt : bluetoothDeviceMap.values()) {
                list.add(bt.getName() + "\n" + bt.getAddress()); //Get the device's name and the address
            }
        }
        final ArrayAdapter adapter = new ArrayAdapter(mainActivity, android.R.layout.simple_list_item_1, list);
        mainActivity.list.setAdapter(adapter);
        mainActivity.list.setOnItemClickListener(myListClickListener); //Method called when the device from the list is clicked
    }

    public void onDestroy() {
        mainActivity.unregisterReceiver(mReceiver);
    }
}
