package com.example.almohanna.coloryourphotograph;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by user on 27/09/17.
 */
public class ColoringPage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coloringpage);

        // home button
        ImageButton home = (ImageButton) findViewById(R.id.home1);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(ColoringPage.this, Home.class);
                startActivity(homeIntent);
            }
        });
    }

    /**
     * final View targetView = findViewById(R.id.targerView); // Any view from which you want to pick color
     new EyeDropper(targetView, new ColorSelectionListener() {
    @Override
    public void onColorSelected(@ColorInt int color) {
    // color is the color selected when you touch the targetView
    (findViewById(R.id.colorSlot)).setBackgroundColor(color);
    }
    });
     */
}

