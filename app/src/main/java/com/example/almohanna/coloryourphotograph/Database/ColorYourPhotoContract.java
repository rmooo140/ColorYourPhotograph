package com.example.almohanna.coloryourphotograph.Database;

import android.provider.BaseColumns;

import org.opencv.core.Mat;

/**
 * Created by Reem on 12-Oct-17.
 */

public class ColorYourPhotoContract {

    private ColorYourPhotoContract() {
    }

    public static final class DifficultyEntry implements BaseColumns {

        public final static String TABLE_NAME = "Difficulty";
        ///public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_LEVEL = "level";

    }

    public static final class GalleryEntry implements BaseColumns {

        public final static String TABLE_NAME = "Gallery";
        //public final static String _ID = BaseColumns._ID;
        public final static Mat COLUMN_COLORING_PAGE = null;

    }

    public static final class ToolsEntry implements BaseColumns {

        public final static String TABLE_NAME = "Tools";
        //public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_COLOR = "color";
        public final static int COLUMN_SIZE = 5;

    }
}
