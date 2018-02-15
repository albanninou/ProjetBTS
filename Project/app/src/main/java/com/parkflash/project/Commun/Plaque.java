package com.parkflash.project.Commun;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.parkflash.project.MainActivity;
import com.parkflash.project.R;

/**
 * Created by alban on 12/02/2018.
 */

public class Plaque {
    private boolean isEneable = false;
    private int color = 0;
    private int index;
    ImageView image;



    public Plaque(MainActivity mainActivity, int index){
        this.index = index;
        image = new ImageView(mainActivity);
        image.setScaleX(30);
        image.setScaleY(40);
        image.setX(100 + 150*(index%4));
        image.setY(300 + 500*(index/4));
        setEneable(false);
        final ConstraintLayout ll = (ConstraintLayout)mainActivity.findViewById(R.id.layout);
        final ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.TOP, ConstraintLayout.LayoutParams.START);
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ll.addView(image, lp);
                update();
            }
        });



    }

    public void setEneable(boolean isEneable){
        this.isEneable = isEneable;
        if(isEneable){
            setColor(color);
        }else{
            image.setImageResource(R.drawable.off);
        }
    }

    public void update(){
        setEneable(isEneable);
        setColor(color);
    }
    public void setColor(int color){
        this.color = color;
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

}
