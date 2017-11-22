package com.example.almohanna.coloryourphotograph;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


public class ColoringPage extends Activity {
    Bitmap photo;
    ImageView viewPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coloringpage);

        photo = (Bitmap)this.getIntent().getParcelableExtra("Bitmap2");
        setContentView(R.layout.coloringpage);
        viewPhoto = (ImageView)findViewById(R.id.img);
        viewPhoto.setImageBitmap(photo);

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


}

