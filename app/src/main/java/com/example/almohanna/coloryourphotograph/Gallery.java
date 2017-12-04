package com.example.almohanna.coloryourphotograph;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.almohanna.coloryourphotograph.Database.ColorYourPhotoDbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 27/09/17.
 */
public class Gallery extends Activity {

    //Bitmap photo;
    //ImageView viewPhoto;
    ColorYourPhotoDbHelper colorYourPhotoDbHelper = new ColorYourPhotoDbHelper(this);
    ArrayList<Images> imageArry= new ArrayList<Images>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);

        //photo = this.getIntent().getParcelableExtra("Bitmap2");
        //viewPhoto = (ImageView) findViewById(R.id.imgIcon);

        //retrive single image
        //viewPhoto = (ImageView) findViewById(R.id.img);
        //Bitmap b = colorYourPhotoDbHelper.retriveImage();
        //viewPhoto.setImageBitmap(b);

        List<Images> list = colorYourPhotoDbHelper.retrieveAllImage();
        //setContentView
        for (int i = 0; i < list.size(); i++) {

            Images img = list.get(i);
            imageArry.add(img);
        }

        ImageAdapter adapter = new ImageAdapter(this, imageArry);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

/*
        // Reading all contacts from database
        List<Images> contacts = colorYourPhotoDbHelper.retrieveImage();
        for (Images cn : contacts) {
            String log = "Image: " + cn.getImg();

// Writing Contacts to log
            Log.d("Result: ", log);
//add contacts data in arrayList
            imageArry.add(cn);

        }
        adapter = new ImageAdapter(this, R.layout.gallery, imageArry);
        ListView dataList = (ListView) findViewById(R.id.list);
        dataList.setAdapter(adapter);
*/
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
