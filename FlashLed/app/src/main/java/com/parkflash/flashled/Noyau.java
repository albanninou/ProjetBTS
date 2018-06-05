package com.parkflash.flashled;

import android.content.Context;

import com.hoho.android.usbserial.driver.UsbSerialPort;

import java.util.ArrayList;
import java.util.List;

public class Noyau {

    ColorFonctionnement colorFonctionnement;
    ModeFonctionnement modeFonctionnement;
    Communication communication;
    MainActivity mainActivity;
    Context context;
    int nbPlaque;

    public List<Plaque> getPlaques() {
        return plaques;
    }

    List<Plaque> plaques = new ArrayList<>();

    public Noyau(MainActivity mainActivity,Context context,ColorFonctionnement colorFonctionnement, ModeFonctionnement modeFonctionnement,int nbPlaque){
        this.colorFonctionnement = colorFonctionnement;
        this.modeFonctionnement = modeFonctionnement;
        this.context = context;
        this.nbPlaque = nbPlaque;
        this.mainActivity = mainActivity;
        for(int i = 0; i < nbPlaque;i++){
            Plaque plaque = new Plaque(mainActivity,i);
            plaque.setColor(colorFonctionnement.getColorFonctionnement());
            plaques.add(plaque);
        }
    }

    public void setDataReceive(final String dataReceive){
        mainActivity.dataReceive.setText(dataReceive);
    }

    public void send() {
        if(communication != null){
            communication.sendData("master:"+modeFonctionnement.getFonctionnementMode()+"/"+colorFonctionnement.getColorFonctionnement()+";\n");
        }else{
            setDataReceive("Le module xbee ne semble pas branché");
        }
    }

    public void setPort(UsbSerialPort port) {
        communication = new Communication(this,port);
        communication.start();
    }
}
