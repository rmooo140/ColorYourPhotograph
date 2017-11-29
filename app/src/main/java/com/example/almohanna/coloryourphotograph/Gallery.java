package com.example.almohanna.coloryourphotograph;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.almohanna.coloryourphotograph.Database.ColorYourPhotoContract;
import com.example.almohanna.coloryourphotograph.Database.ColorYourPhotoDbHelper;

/**
 * Created by user on 27/09/17.
 */
public class Gallery extends Activity {

    Bitmap photo;
    ImageView viewPhoto;
    ColorYourPhotoDbHelper colorYourPhotoDbHelper = new ColorYourPhotoDbHelper(this);

    byte b [];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);

        photo = this.getIntent().getParcelableExtra("Bitmap2");
        viewPhoto = (ImageView) findViewById(R.id.imgIcon);

        // Read difficulty level
        Cursor cursor = colorYourPhotoDbHelper.readImage();

        int ImageColumnIndex = cursor.getColumnIndex(ColorYourPhotoContract.GalleryEntry.COLUMN_COLORING_PAGE);
        // Iterate through all the returned rows in the cursor
        while (cursor.moveToNext()) {
           b = cursor.getBlob(ImageColumnIndex);
            Log.v("image", "retrived correctly ");
        }
        //Bitmap bb= b
        //viewPhoto.setImageBitmap(   bm);
        // home button
        ImageButton home = (ImageButton) findViewById(R.id.home1);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(Gallery.this, Home.class);
                startActivity(homeIntent);
            }
        });
    }
}
