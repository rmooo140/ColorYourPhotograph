package com.example.almohanna.coloryourphotograph;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by user on 27/09/17.
 */
public class Threshed extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threshed);

        Bitmap photo = this.getIntent().getParcelableExtra("Bitmap");
        ImageView viewPhoto = (ImageView) findViewById(R.id.img);
        viewPhoto.setImageBitmap(photo);

        // home button
        ImageButton home = (ImageButton) findViewById(R.id.home1);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(Threshed.this, Home.class);
                startActivity(homeIntent);
            }
        });

        // start coloring button
        TextView startColoringButton = (TextView) findViewById(R.id.startColoringButton);
        startColoringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent coloringIntent = new Intent(Threshed.this, ColoringPage.class);
                startActivity(coloringIntent);
            }
        });
    }

    public void showAlert(View view){
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setMessage("اهلا بك ياصغيري... " + System.getProperty("line.separator")
                +"تستطيع من خلال الحد الاعلى والحد الادنى للخطوط " + System.getProperty("line.separator")
                +"تحديد مستوى الصعوبة التي تريدها " +System.getProperty("line.separator")
                + "اتمنى لك وقتاً ممتعاً ").create();
        myAlert.show();

    }


/**
    SeekBar upperSeekBar = (SeekBar)findViewById(R.id.seekBar1);
    SeekBar lowerSeekBar = (SeekBar)findViewById(R.id.seekBar2);

     seekBar.setMax(your_max_value);

     onProgressChanged method

     // I think this two variables should be out of the method
     int limit = ((20 * 200) / 100);
     int maxValue = seekBar.getMax();
     if(seekBar.getProgress() >= (maxValue - limit)){
     seekBar.setProgress(maxValue);
     }else if(seekBar.getProgress() <= limit){
     seekBar.setProgress(limit);
     }
     */
}
