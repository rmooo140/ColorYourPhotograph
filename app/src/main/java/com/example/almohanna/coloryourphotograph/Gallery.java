package com.example.almohanna.coloryourphotograph;

import android.app.Activity;
import android.os.Bundle;
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
    ArrayList<byte[]> imageArry = new ArrayList<byte[]>();
    ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maingallery);

        //photo = this.getIntent().getParcelableExtra("Bitmap2");
        //viewPhoto = (ImageView) findViewById(R.id.imgIcon);

        //retrive single image
        /*
        viewPhoto = (ImageView) findViewById(R.id.img);
        Bitmap b = colorYourPhotoDbHelper.retriveImage();
        viewPhoto.setImageBitmap(b);
*/
        List<byte[]> list = colorYourPhotoDbHelper.retrieveAllImages();
        for (int i = 0; i < list.size(); i++) {

            byte[] img = list.get(i);
            imageArry.add(img);

        }

        ListView listView = (ListView) findViewById(R.id.list);
        adapter = new ImageAdapter(this, imageArry);
        listView.setAdapter(adapter);

        // home button
        /*
        ImageButton home = (ImageButton) findViewById(R.id.home1);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(Gallery.this, Home.class);
                startActivity(homeIntent);
            }
        });*/
    }
}
