
package com.parkflash;

/**
 * Created by alban on 16/01/2018.
 */
public class ColorFonctionnement extends AdapterView.OnItemSelectedListener {
  public static String COLOR =  "color";

  MainActivity mainActivity;

  int colorFonctionnement;

  public  ColorFonctionnement(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
  }

  public int getColorFonctionnement() {
        return colorFonctionnement;
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
