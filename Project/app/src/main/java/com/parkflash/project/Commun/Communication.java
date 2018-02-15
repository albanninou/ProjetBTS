package com.parkflash.project.Commun;

import com.parkflash.project.ColorFonctionnement;
import com.parkflash.project.ModeFonctionnement;

/**
 * Created by alban on 12/02/2018.
 */

public class Communication {

    Noyau noyau;

    public Communication(Noyau noyau){
        this.noyau = noyau;
    }


    public static void write(ColorFonctionnement colorFonctionnement, ModeFonctionnement modeFonctionnement) {
        int color = colorFonctionnement.getColorFonctionnement();
        int mode = modeFonctionnement.getFonctionnementMode();
    }

    public void receiveData(String data) {
        String[] args = data.split("/");
        if(args.length < 3){
            return;
        }
        noyau.getPlaques().get(Integer.parseInt(args[0])).setEneable(Boolean.parseBoolean(args[1]));
        noyau.getPlaques().get(Integer.parseInt(args[0])).setColor(Integer.parseInt(args[2]));
    }
}
