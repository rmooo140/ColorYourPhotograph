package com.example.almohanna.coloryourphotograph;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Reem on 23-Nov-17.
 */

public class ImageAdapter extends ArrayAdapter<byte[]> {

    Context context;
    ArrayList<byte[]> images;
    //public ColorYourPhotoDbHelper DbHelper = new ColorYourPhotoDbHelper(this.getContext());

    public ImageAdapter(Context context, ArrayList<byte[]> images) {
        super(context, 0, images);
        this.context = context;
        this.images = images;
    }


    //retriva all images
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.gallery, parent, false);
        }
        //Bitmap photo = context.getIntent().getParcelableExtra("Bitmap2");

        ImageView coloringPage = (ImageView) listItemView.findViewById(R.id.brush);
        coloringPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, ColoringPage.class);
                //intent.putExtra("Bitmap", photo );
                context.startActivity(intent);
            }
        });
        ImageView deleteImage = (ImageView) listItemView.findViewById(R.id.drop);
        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  DbHelper.DeleteImage(photo);
                Log.i("adapter", " image deleted from database successfully");
            }
        });
        ImageView imgView = (ImageView) listItemView.findViewById(R.id.img);
        byte[] retrivedImage = images.get(position);
        imgView.setImageBitmap(BitmapFactory.decodeByteArray(retrivedImage, 0, retrivedImage.length));
        return listItemView;
    }
}

