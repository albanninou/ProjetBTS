
package com.parkflash;

/**
 * Created by alban on 16/01/2018.
 */
public class ModeFonctionnement extends AdapterView.OnItemSelectedListener {
  public static String MODE =  "mode";

  MainActivity mainActivity;

  int fonctionnementMode;

  public  ModeFonctionnement(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
  }

  public int getFonctionnementMode() {
        return fonctionnementMode;
  }

  @Override
  public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mainActivity,
                R.array.fonction_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fonctionnementMode = adapter.getPosition(adapterView.getItemAtPosition(i).toString());
  }

  @Override
  public void onNothingSelected(AdapterView<?> adapterView) {

  }

}
