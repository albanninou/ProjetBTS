package com.parkflash.flashled;

import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

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

        DisplayMetrics displayMetrics = new DisplayMetrics();
        mainActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels;
        final int width = displayMetrics.widthPixels;
        final GridLayout gridLayout = mainActivity.findViewById(R.id.gridLayout);

        image = new ImageView(mainActivity);
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

        image.getLayoutParams().width = (int) (width / 4.5f);
        image.getLayoutParams().height = (int) (height / 6f);

        gridLayout.addView(image);
        gridLayout.addView(text);

        update();
    }

    public void setCharge(final int charge) {
        this.charge = charge;
        text.setText("      " + charge + "%");
    }

    public void setEneable(boolean isEneable) {
        this.isEneable = isEneable;
        setColor(color);
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

                    image.setImageResource(R.drawable.p_red);
                    break;
                case 1:
                    image.setImageResource(R.drawable.p_green);
                    break;
                case 2:
                    image.setImageResource(R.drawable.p_blue);
                    break;
            }
        } else {
            image.setImageResource(R.drawable.p_gray);
        }
    }
}
