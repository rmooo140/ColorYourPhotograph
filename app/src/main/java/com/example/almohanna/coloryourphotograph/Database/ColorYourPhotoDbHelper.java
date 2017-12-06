package com.example.almohanna.coloryourphotograph.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import static com.example.almohanna.coloryourphotograph.Database.ColorYourPhotoContract.DifficultyEntry;
import static com.example.almohanna.coloryourphotograph.Database.ColorYourPhotoContract.GalleryEntry;
import static com.example.almohanna.coloryourphotograph.Database.ColorYourPhotoContract.ToolsEntry;

/**
 * Created by Reem on 12-Oct-17.
 */

public class ColorYourPhotoDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = ColorYourPhotoDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "ColorYourPhoto.db";
    private static final int DATABASE_VERSION = 4;
    private ArrayList<byte []> listofImages= new ArrayList<byte []>();

    public ColorYourPhotoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(LOG_TAG, "in Color Your Photo database ");
    }

    String CREATE_TABLE_Difficulty = "CREATE TABLE IF NOT EXISTS " + DifficultyEntry.TABLE_NAME +
            " ( " + DifficultyEntry.COLUMN_LEVEL + " TEXT NOT NULL " + " ); ";

    String CREATE_TABLE_Gallery = "CREATE TABLE IF NOT EXISTS " + GalleryEntry.TABLE_NAME +
            " ( " + GalleryEntry.COLUMN_COLORING_PAGE + " BLOB NOT NULL " + " ); ";

    String CREATE_TABLE_TOOLS = "CREATE TABLE IF NOT EXISTS " + ToolsEntry.TABLE_NAME +
            " ( " + ToolsEntry.COLUMN_NAME + " TEXT ," +
            ToolsEntry.COLUMN_COLOR + " TEXT ," +
            ToolsEntry.COLUMN_SIZE + " INTEGER " + " ); ";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_Difficulty);
        sqLiteDatabase.execSQL(CREATE_TABLE_Gallery);
        sqLiteDatabase.execSQL(CREATE_TABLE_TOOLS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DifficultyEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GalleryEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ToolsEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    public void insertDifficultyLevel(String level) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DifficultyEntry.COLUMN_LEVEL, level);
        db.insert(DifficultyEntry.TABLE_NAME, null, values);
        //db.close();
    }

    public void insertTools(String name, String color, int size) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ToolsEntry.COLUMN_NAME, name);
        values.put(ToolsEntry.COLUMN_COLOR, color);
        values.put(String.valueOf(ToolsEntry.COLUMN_SIZE), size);
        db.insert(ToolsEntry.TABLE_NAME, null, values);
       // db.close();
    }

    public void insertImage(byte[] image) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GalleryEntry.COLUMN_COLORING_PAGE, image);
        db.insert(GalleryEntry.TABLE_NAME, null, values);
       // db.close();
    }

    public Cursor readLevel() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {DifficultyEntry.COLUMN_LEVEL,};
        Cursor cursor = db.query(
                DifficultyEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
      //  db.close();
        return cursor;
    }

    public ArrayList<byte []> retrieveAllImages() {
        listofImages.clear();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {GalleryEntry.COLUMN_COLORING_PAGE,};
        Cursor cursor = db.query(
                GalleryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do {
                    byte [] blob = cursor.getBlob(cursor.getColumnIndex(GalleryEntry.COLUMN_COLORING_PAGE));
                    listofImages.add(blob);
                }
                while(cursor.moveToNext());
            }
        }

        //cursor.close();
      //  db.close();
        return listofImages;
    }

    // Getting single contact
    public Bitmap retriveImage() {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] projection = {GalleryEntry.COLUMN_COLORING_PAGE,};
        Cursor cursor = db.query(
                GalleryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor != null)
            cursor.moveToFirst();

        byte [] img = cursor.getBlob(0);
        ByteArrayInputStream imageStream = new ByteArrayInputStream(img);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);

        return theImage;
    }

    public void DeleteImage( byte image) {

        SQLiteDatabase db = this.getWritableDatabase();
        //String query = "DELETE FROM " + GalleryEntry.TABLE_NAME + " WHERE" + GalleryEntry.COLUMN_COLORING_PAGE + " = '" + image + "'";
        db.delete(GalleryEntry.TABLE_NAME,
                GalleryEntry.COLUMN_COLORING_PAGE + "=?",
                new String[]{String.valueOf(image)});
           // db.close();
        // db.execSQL(query);
    }
/*
    private int deleteOneItemFromTable(long itemId) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String selection = StockContract.StockEntry._ID + "=?";
        String[] selectionArgs = {String.valueOf(itemId)};
        int rowsDeleted = database.delete(
                StockContract.StockEntry.TABLE_NAME, selection, selectionArgs);
        return rowsDeleted;
    }
*/
}
