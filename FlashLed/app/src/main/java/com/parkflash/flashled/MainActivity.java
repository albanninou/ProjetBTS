package com.parkflash.flashled;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ModeFonctionnement modeFonctionnement;
    ColorFonctionnement colorFonctionnement;
    Button sendButton;

    TextView dataReceive;
    UsbSerialPort port;
    Noyau noyau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sendButton = findViewById(R.id.button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noyau.send();
            }
        });

        modeFonctionnement = new ModeFonctionnement(this);
        colorFonctionnement = new ColorFonctionnement(this);

        dataReceive = findViewById(R.id.receivedData);

        //spinner for chose fonctionnement
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.fonction_array,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(modeFonctionnement);

        //spinner for chose color
        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.color_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(colorFonctionnement);

        noyau = new Noyau(this,this,colorFonctionnement,modeFonctionnement,8);
        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers.isEmpty()) {
            return;
        }
        // Open a connection to the first available driver.
        UsbSerialDriver driver = availableDrivers.get(0);
        UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
        if (connection == null) {
            return;
        }

        // Récuperation du port serie
        port = driver.getPorts().get(0);
        try {
            port.open(connection);
            port.setParameters(38400, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
            noyau.setPort(port);
        } catch (IOException e) {
            // Deal with error.
        }
    }

    /*
     * Notifications from UsbService will be received here.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            port.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
