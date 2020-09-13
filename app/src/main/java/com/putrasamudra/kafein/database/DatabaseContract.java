package com.putrasamudra.kafein.database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_CAFE = "Cafe";

    public static final class CafeColumns implements BaseColumns {
        public static String NAME = "name";
        public static String PHOTO = "photo";
        public static String PHOTO2 = "photo2";
        public static String MAXPEOPLE = "maxpeople";
        public static String POSITION = "position";
        public static String RATING = "rating";
    }

    public static final String AUTHORITY = "com.putrasamudra.kafein";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_CAFE)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }
}