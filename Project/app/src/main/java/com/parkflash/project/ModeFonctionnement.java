package com.parkflash.project;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by alban on 16/01/2018.
 */

public class ModeFonctionnement implements AdapterView.OnItemSelectedListener {
    MainActivity mainActivity;

    int fonctionnementMode;


    public ModeFonctionnement(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView textView = (TextView) mainActivity.findViewById(R.id.textView);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mainActivity,
                R.array.fonction_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fonctionnementMode = adapter.getPosition(adapterView.getItemAtPosition(i).toString());

        textView.setText("change fonctionnement");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
