package com.example.almohanna.coloryourphotograph;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.almohanna.coloryourphotograph.Database.ColorYourPhotoDbHelper;

import java.util.ArrayList;

/**
 * Created by Reem on 23-Nov-17.
 */

public class ImageAdapter extends ArrayAdapter<byte[]> {

    Context context;
    ArrayList<byte[]> images;
  //  Bitmap imgBitmap;
    ColorYourPhotoDbHelper DbHelper = new ColorYourPhotoDbHelper(this.getContext());

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

        ImageView imgView = (ImageView) listItemView.findViewById(R.id.img);
        byte[] retrivedImage = images.get(position);
    //    imgBitmap = BitmapFactory.decodeByteArray(retrivedImage, 0, retrivedImage.length);
      //  imgView.setImageBitmap(imgBitmap);
          Bitmap tempBitmap = BitmapFactory.decodeByteArray(retrivedImage,0,retrivedImage.length);
          imgView.setImageBitmap(tempBitmap);
          ImageView coloringPage = (ImageView) listItemView.findViewById(R.id.brush);
          coloringPage.setTag(tempBitmap);
          coloringPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, ColoringPage.class);
                //intent.putExtra("Bitmap2", imgBitmap);
                intent.putExtra("Bitmap2",(Bitmap)v.getTag());
                context.startActivity(intent);
            }
        });
        ImageView deleteImage = (ImageView) listItemView.findViewById(R.id.drop);
        deleteImage.setTag(tempBitmap);
        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DbHelper.DeleteImage(imgBitmap);
                DbHelper.DeleteImage((Bitmap)v.getTag());
                Log.i("adapter", " image deleted from database successfully");
            }
        });
        return listItemView;
    }
}

