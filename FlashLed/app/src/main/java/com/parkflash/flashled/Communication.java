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


    //data receive
    public void receiveData(String data) {
        noyau.setDataReceive(data);
        // split all line with '\n'
        final String[] ligne = data.split("\n");
        for (String l : ligne) {
            // in all line split with '/'
            final String[] args = l.split("/");
            if (args.length == 4) {

                try {
                    //check if plaque is on or off
                    boolean eneable = false;
                    if ("1".equalsIgnoreCase(args[1])) {
                        eneable = true;
                    }
                    //set the state on view
                    noyau.getPlaques().get(Integer.parseInt(args[0])).setEneable(eneable);
                    //set the color on view
                    noyau.getPlaques().get(Integer.parseInt(args[0])).setColor(Integer.parseInt(args[2]));
                    //set the lvl of charge in view
                    noyau.getPlaques().get(Integer.parseInt(args[0])).setCharge(Integer.parseInt(args[3]));
                } catch (Exception e) {
                    //parseInt fail , a bad caractere are receive
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public void run(){
        //read all time
        while(run){
            final byte buffer[] = new byte[512];
            try {
                //read data receive
                int numBytesRead = port.read(buffer, 100);
                //check if you read a byte or not cause numBytesRead can be null , cause of timeout
                if(numBytesRead > 0) {
                    noyau.mainActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            receiveData(new String(buffer));
                        }
                    });
                }
            } catch(IOException e){
               run = false;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendData(String data){
        data+="\n";
        byte buffer[] = data.getBytes();
        try {
            //send the data on port usb
            port.write(buffer,50);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
