package com.example.almohanna.coloryourphotograph.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.almohanna.coloryourphotograph.Database.ColorYourPhotoContract.DifficultyEntry;
import static com.example.almohanna.coloryourphotograph.Database.ColorYourPhotoContract.GalleryEntry;
import static com.example.almohanna.coloryourphotograph.Database.ColorYourPhotoContract.ToolsEntry;

/**
 * Created by Reem on 12-Oct-17.
 */

public class ColorYourPhotoDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = ColorYourPhotoDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "ColorYourPhoto.db";
    private static final int DATABASE_VERSION = 1;

    public ColorYourPhotoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(LOG_TAG, "in Color Your Photo database ");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a String that contains the SQL statement to create the difficulty table
        Log.i(LOG_TAG, "in onCreate ");

        String CREATE_TABLE_Difficulty = "CREATE TABLE IF NOT EXISTS " + DifficultyEntry.TABLE_NAME +
                "(" + DifficultyEntry.COLUMN_LEVEL + " TEXT NOT NULL);";
        Log.v("ColorYourPhotoDbHelper", "create table: " + CREATE_TABLE_Difficulty);
        // Execute the SQL statement
        sqLiteDatabase.execSQL(CREATE_TABLE_Difficulty);
        Log.i(LOG_TAG, "in onCreate SQLiteDatabase first table craeted successfully ");

        /////////////////////////////////////////////////////////////////////////


        // Create a String that contains the SQL statement to create the gallary table
        String CREATE_TABLE_Gallery = "CREATE TABLE IF NOT EXISTS " + GalleryEntry.TABLE_NAME +
                "(" + GalleryEntry.COLUMN_COLORING_PAGE + " BLOB  );";

        Log.v("ColorYourPhotoDbHelper", "create table: " + CREATE_TABLE_Gallery);
        // Execute the SQL statement
        sqLiteDatabase.execSQL(CREATE_TABLE_Gallery);
        Log.i(LOG_TAG, "in onCreate SQLiteDatabase second table craeted successfully ");

        /////////////////////////////////////////////////////////////////////////

        // Create a String that contains the SQL statement to create the tools table
        String CREATE_TABLE_TOOLS = "CREATE TABLE IF NOT EXISTS " + ToolsEntry.TABLE_NAME +
                "(" + ToolsEntry.COLUMN_NAME + " TEXT NOT NULL," +
                ToolsEntry.COLUMN_COLOR + " TEXT NOT NULL," +
                ToolsEntry.COLUMN_SIZE + " INTEGER NOT NULL);";

        Log.v("ColorYourPhotoDbHelper", "create table: " + CREATE_TABLE_TOOLS);
        // Execute the SQL statement
        sqLiteDatabase.execSQL(CREATE_TABLE_TOOLS);
        Log.i(LOG_TAG, "in onCreate SQLiteDatabase third table craeted successfully ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DifficultyEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GalleryEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ToolsEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    public void insertDifficultyLevel( String level) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DifficultyEntry.COLUMN_LEVEL, level);
        db.insert(DifficultyEntry.TABLE_NAME, null, values);
    }

    public void insertTools( String name,String color,int size) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ToolsEntry.COLUMN_NAME, name);
        values.put(ToolsEntry.COLUMN_COLOR, color);
        values.put(String.valueOf(ToolsEntry.COLUMN_SIZE), size);
        db.insert(ToolsEntry.TABLE_NAME, null, values);
    }

    public void insertImage( Byte bytes) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(String.valueOf(GalleryEntry.COLUMN_COLORING_PAGE), bytes);
        db.insert(GalleryEntry.TABLE_NAME, null, values);
    }

    public Cursor readLevel() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                DifficultyEntry.COLUMN_LEVEL,
        };
        Cursor cursor = db.query(
                DifficultyEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }
}
