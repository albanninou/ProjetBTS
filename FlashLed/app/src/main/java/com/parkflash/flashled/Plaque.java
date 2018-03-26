package com.parkflash.flashled;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.widget.ImageView;
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



    public Plaque(MainActivity mainActivity, int index){
        this.mainActivity = mainActivity;
        this.index = index;

        text = new TextView(mainActivity);
        if(text == null){
            Toast.makeText(mainActivity,"fiojriojf",Toast.LENGTH_SHORT).show();
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mainActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        float h =(height/720.0f);

        float w =(width/320.0f);
        text.setTextSize((float) 20);
        text.setX((40 + 60*(index%4))*w);
        text.setY((160*(index/4))*h);
        image = new ImageView(mainActivity);
        image.setScaleX(12*w);
        image.setScaleY(20*h);
        image.setX((50 + 60*(index%4))*w);
        image.setY((90 + 160*(index/4))*h);
        setEneable(false);
        setCharge(index);
        ConstraintLayout.LayoutParams clpcontactUs = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        text.setLayoutParams(clpcontactUs);
        final ConstraintLayout ll = (ConstraintLayout)mainActivity.findViewById(R.id.layout);
        final ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.TOP, ConstraintLayout.LayoutParams.START);
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ll.addView(image, lp);
                ll.addView(text);
                update();
            }
        });



    }

    public void setCharge(final int charge){
        this.charge = charge;
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                text.setText(charge + "%");
            }
        });
    }

    public void setEneable(boolean isEneable){
        this.isEneable = isEneable;
        if(isEneable){
            setColor(color);
        }else{
            mainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    image.setImageResource(R.drawable.off);
                }
            });
        }
    }

    public void update(){
        setEneable(isEneable);
        setColor(color);
    }
    public void setColor(final int color){
        this.color = color;
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(isEneable){
                    switch (color){
                        case 0 :
                            image.setImageResource(R.drawable.red);
                            break;
                        case 1 :
                            image.setImageResource(R.drawable.green);
                            break;
                        case 2 :
                            image.setImageResource(R.drawable.blue);
                            break;
                    }
                }else{
                    image.setImageResource(R.drawable.off);
                }
            }
        });

    }

}
