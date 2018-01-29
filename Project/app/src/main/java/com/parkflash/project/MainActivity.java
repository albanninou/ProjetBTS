package com.parkflash.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    public static int REQUEST_ENABLE_BLUETOOTH_BT = 1;
    public static String MY_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    public ListView list;
    ModeFonctionnement modeFonctionnement;
    ColorFonctionnement colorFonctionnement;
    MyPermission myPermission;
    Bluetooth bluetooth;
    Button reScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reScan = findViewById(R.id.button);
        reScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bluetooth.isEneable){
                    bluetooth.startScan();
                }
            }
        });
        list = findViewById(R.id.list);
        bluetooth = new Bluetooth(this);
        modeFonctionnement = new ModeFonctionnement(this);
        colorFonctionnement = new ColorFonctionnement(this);
        myPermission = new MyPermission(this);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.fonction_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(modeFonctionnement);

        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.color_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(colorFonctionnement);

        list = findViewById(R.id.list);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bluetooth.onDestroy();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        myPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, bluetooth);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("ParkFlash", "activity result call");
        if (requestCode == REQUEST_ENABLE_BLUETOOTH_BT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Log.e("ParkFlash", "Result ok");
                if (myPermission.checkLocationPermission()) {
                    bluetooth.isEneable = true;
                    bluetooth.startScan();
                }
            }
        }
    }
}
