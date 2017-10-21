package com.example.almohanna.coloryourphotograph;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.almohanna.coloryourphotograph.Database.ColorYourPhotoDbHelper;

/**
 * Created by user on 27/09/17.
 */
public class Levels extends Activity {

    ColorYourPhotoDbHelper colorYourPhotoDbHelper = new ColorYourPhotoDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);

        Button easy = (Button) findViewById(R.id.easy);
        Button meduim = (Button) findViewById(R.id.meduim);
        Button advance = (Button) findViewById(R.id.advance);


        if (easy.isClickable()) {
            easy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    colorYourPhotoDbHelper.insertDifficultyLevel("easy");
                    Log.i("level", "inseted to database successfully");

                }
            });
        } else
            if (meduim.isClickable()) {
            meduim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    colorYourPhotoDbHelper.insertDifficultyLevel("meduim");
                    Log.i("level", "inseted to database successfully");
                }
            });

        }
        else {
            advance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    colorYourPhotoDbHelper.insertDifficultyLevel("advance");
                    Log.i("level", "inseted to database successfully");
                }
            });
        }

        // home button
        ImageButton home = (ImageButton) findViewById(R.id.home1);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(Levels.this, Home.class);
                startActivity(homeIntent);
            }
        });

    }

}
