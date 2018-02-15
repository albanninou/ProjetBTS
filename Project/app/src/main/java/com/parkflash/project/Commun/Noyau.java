package com.parkflash.project.Commun;

import android.content.Context;
import android.view.ViewGroup;

import com.parkflash.project.ColorFonctionnement;
import com.parkflash.project.MainActivity;
import com.parkflash.project.ModeFonctionnement;
import com.parkflash.project.R;

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
        for(int i = 0; i < nbPlaque;i++){
            Plaque plaque = new Plaque(mainActivity,i);
            plaque.setColor(colorFonctionnement.getColorFonctionnement());
            plaques.add(plaque);
        }
        plaques.get(0).setEneable(true);
        plaques.get(0).setColor(1);
        plaques.get(1).setEneable(true);
        plaques.get(1).setColor(2);
        plaques.get(2).setEneable(true);
        plaques.get(2).setColor(3);
        communication = new Communication(this);
        Communication.write(colorFonctionnement,modeFonctionnement);
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

    public void receiveData(String data){
        communication.receiveData(data);
    }
}
