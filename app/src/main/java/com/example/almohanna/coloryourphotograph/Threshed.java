package com.example.almohanna.coloryourphotograph;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by user on 27/09/17.
 */
public class Threshed extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threshed);


        // home button
        ImageButton home = (ImageButton) findViewById(R.id.home1);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(Threshed.this, Home.class);
                startActivity(homeIntent);
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
     * seekBar = (SeekBar)findViewById(R.id.seekbar_id);
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
