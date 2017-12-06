package com.example.almohanna.coloryourphotograph;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.almohanna.coloryourphotograph.Database.ColorYourPhotoDbHelper;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by Reem on 23-Nov-17.
 */

public class ImageAdapter extends ArrayAdapter<byte []> {

    public ColorYourPhotoDbHelper DbHelper = new ColorYourPhotoDbHelper(this.getContext());

    public ImageAdapter(Context context, ArrayList<byte []> images) {
        super(context, 0, images);
    }

    /*
    //retrive single image
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.gallery, parent, false);
        }
        Images img = getItem(position);

        ImageView view = (ImageView) listItemView.findViewById(img);
        byte[] outImage = img.img;
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap out = BitmapFactory.decodeStream(imageStream);
        view.setImageBitmap(out);

        return listItemView;
    }
*/

    //retriva all images
    public View getView (int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.gallery, parent, false);
        }
        ImageView imgView = (ImageView) listItemView.findViewById(R.id.img);
        ArrayList<byte []> imagesList = DbHelper.retrieveAllImages();// Get all images

        for (int i = 0; i < imagesList.size(); i++) {

            //ImageView img = new ImageView(getContext());
            byte [] retrivedImage= imagesList.get(i);

            //byte[] outImage = retrivedImage; //.img
            ByteArrayInputStream imageStream = new ByteArrayInputStream(retrivedImage);
            Bitmap out = BitmapFactory.decodeStream(imageStream);
            imgView.setImageBitmap(out);

        }
        return listItemView;
    }
    /*
    Context context;
    int layoutResourceId;
    ArrayList<Images> data = new ArrayList<Images>();

    public ImageAdapter(Context context, int layoutResourceId, ArrayList<Images> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ImageHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ImageHolder();
            holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
            row.setTag(holder);
        } else {
            holder = (ImageHolder) row.getTag();
        }
        Images picture = data.get(position);

        //convert byte to bitmap take from contact class
        byte[] outImage = picture.img;
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        holder.imgIcon.setImageBitmap(theImage);
        return row;
    }

    static class ImageHolder {
        ImageView imgIcon;
    }
    */
}

