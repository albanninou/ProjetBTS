package com.parkflash.project;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by alban on 16/01/2018.
 */

public class ColorFonctionnement implements AdapterView.OnItemSelectedListener {
    MainActivity mainActivity;

    public int getColorFonctionnement() {
        return colorFonctionnement;
    }

    int colorFonctionnement;

    public static String COLOR = "color";

    public ColorFonctionnement(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mainActivity,
                R.array.color_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorFonctionnement = adapter.getPosition(adapterView.getItemAtPosition(i).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

