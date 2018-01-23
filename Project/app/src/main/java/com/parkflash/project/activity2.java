package com.parkflash.project;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;



public class activity2 extends AppCompatActivity {
    BluetoothDevice device;
    BluetoothSocket tmp = null;
    BluetoothSocket mmSocket = null;
    BluetoothAdapter mBluetoothAdapter;
    private InputStream mmInStream = null;
    private OutputStream mmOutStream = null;

    private static final String TAG = "MY_APP_DEBUG_TAG";


    // Defines several constants used when transmitting messages between the
    // service and the UI.
    private interface MessageConstants {
        public static final int MESSAGE_READ = 0;
        public static final int MESSAGE_WRITE = 1;
        public static final int MESSAGE_TOAST = 2;
        public static final String TOAST = "Toast";

        // ... (Add other message types here as needed.)
    }

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

        Log.e("parkflash", "on reset le device");
        ConnectThread connectThread = new ConnectThread(device);
        connectThread.start();


        setTitle(device.getName());

    }



    private byte[] mmBuffer; // mmBuffer store for the stream

    public void ConnectedThread() {

        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        // Get the input and output streams; using temp objects because
        // member streams are final.
        try {
            tmpIn = mmSocket.getInputStream();
        } catch (IOException e) {
            Log.e("parkflash", "Error occurred when creating input stream", e);
        }
        try {
            tmpOut = mmSocket.getOutputStream();
        } catch (IOException e) {
            Log.e("parkflash", "Error occurred when creating output stream", e);
        }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;

        write("bonjour".getBytes());
    }
    public void write(byte[] bytes) {
        try {
            mmOutStream.write(bytes);

            // Share the sent message with the UI activity.
            Message writtenMsg = mHandler.obtainMessage(
                    MessageConstants.MESSAGE_WRITE, -1, -1, mmBuffer);
            writtenMsg.sendToTarget();
        } catch (IOException e) {
            Log.e(TAG, "Error occurred when sending data", e);

            // Send a failure message back to the activity.
            Message writeErrorMsg =
                    mHandler.obtainMessage(MessageConstants.MESSAGE_TOAST);
            Bundle bundle = new Bundle();
            bundle.putString("toast",
                    "Couldn't send data to the other device");
            writeErrorMsg.setData(bundle);
            mHandler.sendMessage(writeErrorMsg);
        }
    }

    private Boolean connect(BluetoothDevice bdDevice) {
        Boolean bool = false;
        try {
            Log.e("parkflash", "service method is called ");
            Class cl = Class.forName("android.bluetooth.BluetoothDevice");
            Class[] par = {};
            Method method = cl.getMethod("createBond", par);
            Object[] args = {};
            bool = (Boolean) method.invoke(bdDevice);//, args);// this invoke creates the detected devices paired.
            Log.e("parkflash", "conected to device: "+bool.booleanValue());
            try {

                mmSocket = device.createRfcommSocketToServiceRecord(UUID.fromString(MainActivity.MY_UUID));
//            Method m = device.getClass().getMethod("createRfcommSocket", new Class[]{int.class});
//            tmp = (BluetoothSocket) m.invoke(device, 1);

            } catch (Exception e) {
                e.printStackTrace();
            }
            if(mmSocket == null){
                Log.e("parkflash", "socket null --'");

            }
                //ConnectedThread();
            //Log.i("Log", "devicesss: "+bdDevice.getName());
        } catch (Exception e) {
            Log.i("Log", "Inside catch of serviceFromDevice Method");
            this.finish();
            e.printStackTrace();
        }
        return bool.booleanValue();
    }

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
//                case MESSAGE_STATE_CHANGE:
//                    if(D) Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
//                    switch (msg.arg1) {
//                        case BluetoothChatService.STATE_CONNECTED:
//                            mTitle.setText(R.string.title_connected_to);
//                            mTitle.append(mConnectedDeviceName);
//                            mConversationArrayAdapter.clear();
//                            break;
//                        case BluetoothChatService.STATE_CONNECTING:
//                            mTitle.setText(R.string.title_connecting);
//                            break;
//                        case BluetoothChatService.STATE_LISTEN:
//                        case BluetoothChatService.STATE_NONE:
//                            mTitle.setText(R.string.title_not_connected);
//                            break;
//                    }
//                    break;
                case MessageConstants.MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);

                    break;
                case MessageConstants.MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readMessage = new String(readBuf, 0, msg.arg1);

                    break;
                case MessageConstants.MESSAGE_TOAST:
                    Toast.makeText(getApplicationContext(), msg.getData().getString(MessageConstants.TOAST),
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            // Use a temporary object that is later assigned to mmSocket,
            // because mmSocket is final
            BluetoothSocket tmp = null;
            mmDevice = device;

            // Get a BluetoothSocket to connect with the given BluetoothDevice
            try {
                // MY_UUID is the app's UUID string, also used by the server code
                tmp = device.createRfcommSocketToServiceRecord(UUID.fromString(MainActivity.MY_UUID));
            } catch (IOException e) { }
            mmSocket = tmp;
            if(mmSocket == null){
                Log.e("parkflash", "socket null --'");

            }
        }

        public void run() {
            // Cancel discovery because it will slow down the connection
            mBluetoothAdapter.cancelDiscovery();
            Log.e("ListDeviceBluetooth", "Initiation de la connexion");
            try {
                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception

                mmSocket.connect();
                //Toast.makeText(getActivity().getApplicationContext(),"Appairage effectué",Toast.LENGTH_LONG).show();
                Log.e("ListDeviceBluetooth", "Appairage effectué");
            } catch (IOException connectException) {
                // Unable to connect; close the socket and get out
                Log.e("ListDeviceBluetooth", "Impossible de se connecter");
                //Toast.makeText(getApplicationContext(),"Impossible de se connecter",Toast.LENGTH_LONG).show();
                try {
                    mmSocket.close();
                } catch (IOException closeException) { }
                return;
            }

            // Do work to manage the connection (in a separate thread)
            // manageConnectedSocket(mmSocket);
        }

        /** Will cancel an in-progress connection, and close the socket */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }
}
