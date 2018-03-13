package com.parkflash.flashled;

import android.widget.Toast;

import com.hoho.android.usbserial.driver.UsbSerialPort;

import java.io.IOException;

/**
 * Created by alban on 12/02/2018.
 */

public class Communication extends Thread{

    Noyau noyau;
    boolean run = true;
    UsbSerialPort port;


    public Communication(Noyau noyau,UsbSerialPort port){
        this.noyau = noyau;
        this.port = port;
    }

    public static void write(ColorFonctionnement colorFonctionnement, ModeFonctionnement modeFonctionnement) {
        int color = colorFonctionnement.getColorFonctionnement();
        int mode = modeFonctionnement.getFonctionnementMode();

    }

    public void receiveData(String data) {
        noyau.setDataReceive(data);
        final String[] ligne = data.split("\r");
        for(String l:ligne) {
            final String[] args = l.split("/");
            if (args.length < 3) {
                continue;
            }//1/1/2
            noyau.mainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        boolean eneable = false;
                        if ("1".equalsIgnoreCase(args[1])) {
                            eneable = true;
                        }
                        noyau.getPlaques().get(Integer.parseInt(args[0])).setEneable(eneable);
                        noyau.getPlaques().get(Integer.parseInt(args[0])).setColor(Integer.parseInt(args[2].substring(0, 1)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void run(){
        while(run){
            byte buffer[] = new byte[512];
            try {
                int numBytesRead = port.read(buffer, 50);
                if(numBytesRead > 0) {
                    receiveData(new String(buffer));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendData(String data){
        data+="\n";
        byte buffer[] = data.getBytes();
        try {
            port.write(buffer,50);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
