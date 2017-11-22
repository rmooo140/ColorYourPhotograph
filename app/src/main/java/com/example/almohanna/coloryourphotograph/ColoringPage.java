package com.example.almohanna.coloryourphotograph;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.almohanna.coloryourphotograph.Database.ColorYourPhotoDbHelper;

import java.io.ByteArrayOutputStream;


public class ColoringPage extends Activity {
    Bitmap photo;
    ImageView viewPhoto;
    ColorYourPhotoDbHelper colorYourPhotoDbHelper = new ColorYourPhotoDbHelper(this);

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

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte imageInByte[] = stream.toByteArray();
                colorYourPhotoDbHelper.insertImage(imageInByte);
                Log.i("images", "inserted to database successfully");

                Intent homeIntent = new Intent(ColoringPage.this, Home.class);
                startActivity(homeIntent);
            }
        });
    }


}

