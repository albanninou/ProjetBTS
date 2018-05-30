package com.parkflash.flashled;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by alban on 12/02/2018.
 */

public class Plaque {
    private boolean isEneable = false;
    private int color = 0;
    private int index;
    private int charge = 0;
    ImageView image;
    TextView text;
    MainActivity mainActivity;


    public Plaque(MainActivity mainActivity, int index) {
        this.mainActivity = mainActivity;
        this.index = index;

        text = new TextView(mainActivity);
        if (text == null) {
            Toast.makeText(mainActivity, "fiojriojf", Toast.LENGTH_SHORT).show();
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mainActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels;
        final int width = displayMetrics.widthPixels;
        final GridLayout gridLayout = mainActivity.findViewById(R.id.gridLayout);
        final LinearLayout container = mainActivity.findViewById(R.id.container);
        float h = gridLayout.getHeight();
        float w = gridLayout.getWidth();
        //text.setTextSize((float) 20);
        //text.setX((40 + 60*(index%4))*w);
        //text.setY((160*(index/4))*h);
        image = new ImageView(mainActivity);

        //image.setX((50 + 60*(index%4))*w);
        //image.setY((90 + 160*(index/4))*h);*/
        setEneable(false);
        setCharge(index);
        ConstraintLayout.LayoutParams clpcontactUs = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        text.setLayoutParams(clpcontactUs);

        text.setLayoutParams(
                new GridLayout.LayoutParams(
                        GridLayout.spec((index / 4) * 2, GridLayout.FILL),
                        GridLayout.spec((index % 4), GridLayout.FILL)
                ));
        image.setLayoutParams(
                new GridLayout.LayoutParams(
                        GridLayout.spec(((index / 4) + 1) * 2 - 1, GridLayout.FILL),
                        GridLayout.spec((index % 4), GridLayout.FILL)
                ));

        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                gridLayout.addView(image);
                gridLayout.addView(text);
                image.getLayoutParams().width = (int) (width / 4.5f);
                image.getLayoutParams().height = (int) (height / 6f);
                image.requestLayout();
                gridLayout.requestLayout();
                update();
            }
        });


    }

    public void setCharge(final int charge) {
        this.charge = charge;
        text.setText("      " + charge + "%");
    }

    public void setEneable(boolean isEneable) {
        this.isEneable = isEneable;
        if (isEneable) {
            setColor(color);
        } else {
            image.setImageResource(R.drawable.off);
        }
    }

    public void update() {
        setEneable(isEneable);
        setColor(color);
    }

    public void setColor(final int color) {
        this.color = color;
        if (isEneable) {
            switch (color) {
                case 0:
                    image.setImageResource(R.drawable.red);
                    break;
                case 1:
                    image.setImageResource(R.drawable.green);
                    break;
                case 2:
                    image.setImageResource(R.drawable.blue);
                    break;
            }
        } else {
            image.setImageResource(R.drawable.off);
        }
    }

}
