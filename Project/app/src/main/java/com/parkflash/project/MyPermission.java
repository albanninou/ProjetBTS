
package com.parkflash;

/**
 * Created by alban on 29/01/2018.
 */
public class MyPermission {
  MainActivity mainActivity;

  public  MyPermission(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
  }

  public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mainActivity,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        }else{
            return true;
        }
        return false;
  }

  public void onRequestPermissionsResult(int requestCode, String permissions, int grantResults, Bluetooth bluetooth) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    bluetooth.startScan();
                } else {
                    ActivityCompat.requestPermissions(mainActivity,
                            new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                            1);
                }
                break;
            }
        }
  }

}
