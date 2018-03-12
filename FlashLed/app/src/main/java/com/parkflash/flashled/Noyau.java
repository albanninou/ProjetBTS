package com.parkflash.flashled;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alban on 12/02/2018.
 */

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
        communication = new Communication(this,mainActivity.port);
        communication.start();
    }

    public ColorFonctionnement getColorFonctionnement() {
        return colorFonctionnement;
    }

    public void setColorFonctionnement(ColorFonctionnement colorFonctionnement) {
        this.colorFonctionnement = colorFonctionnement;
    }

    public ModeFonctionnement getModeFonctionnement() {
        return modeFonctionnement;
    }

    public void setModeFonctionnement(ModeFonctionnement modeFonctionnement) {
        this.modeFonctionnement = modeFonctionnement;
    }

    public void setDataReceive(final String dataReceive){
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //mainActivity.dataReceive.getText();
                mainActivity.dataReceive.setText(dataReceive);
            }
        });
    }

    public void send() {
        communication.sendData("master:"+modeFonctionnement.getFonctionnementMode()+"/"+colorFonctionnement.getColorFonctionnement());
    }
}
