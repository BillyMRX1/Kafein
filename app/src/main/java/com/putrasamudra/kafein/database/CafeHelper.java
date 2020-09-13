package com.putrasamudra.kafein.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.putrasamudra.kafein.model.Cafe;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.putrasamudra.kafein.database.DatabaseContract.CafeColumns.MAXPEOPLE;
import static com.putrasamudra.kafein.database.DatabaseContract.CafeColumns.NAME;
import static com.putrasamudra.kafein.database.DatabaseContract.CafeColumns.PHOTO;
import static com.putrasamudra.kafein.database.DatabaseContract.CafeColumns.PHOTO2;
import static com.putrasamudra.kafein.database.DatabaseContract.CafeColumns.POSITION;
import static com.putrasamudra.kafein.database.DatabaseContract.CafeColumns.RATING;
import static com.putrasamudra.kafein.database.DatabaseContract.TABLE_CAFE;

public class CafeHelper {
    private static final String DATABASE_TABLE = TABLE_CAFE;
    private static DatabaseHelper dataBaseHelper;
    private static CafeHelper INSTANCE;
    private static SQLiteDatabase database;

    public CafeHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static CafeHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CafeHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }

    public void close() {
        dataBaseHelper.close();
        if (database.isOpen())
            database.close();
    }

    public ArrayList<Cafe> getAllMovies() {
        ArrayList<Cafe> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        Cafe cafe;
        if (cursor.getCount() > 0) {
            do {
                cafe = new Cafe();
                cafe.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                cafe.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                cafe.setPhoto(cursor.getString(cursor.getColumnIndexOrThrow(PHOTO)));
                cafe.setPhoto2(cursor.getString(cursor.getColumnIndexOrThrow(PHOTO2)));
                cafe.setMaxpeople(cursor.getString(cursor.getColumnIndexOrThrow(MAXPEOPLE)));
                cafe.setPosition(cursor.getString(cursor.getColumnIndexOrThrow(POSITION)));
                cafe.setRating(cursor.getFloat(cursor.getColumnIndexOrThrow(RATING)));
                arrayList.add(cafe);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(Cafe cafe) {
        ContentValues args = new ContentValues();
        args.put(_ID, cafe.getId());
        args.put(NAME, cafe.getName());
        args.put(PHOTO, cafe.getPhoto());
        args.put(PHOTO2, cafe.getPhoto2());
        args.put(MAXPEOPLE, cafe.getMaxpeople());
        args.put(POSITION, cafe.getPosition());
        args.put(RATING, cafe.getRating());
        return database.insert(DATABASE_TABLE, null, args);
    }

    public int delete(int id) {
        return database.delete(DATABASE_TABLE, _ID + " = '" + id + "'", null);
    }

    public boolean isExist(int id) {
        String query = "SELECT * FROM " + DATABASE_TABLE + " WHERE " + _ID + " =?";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id)});
        boolean exist = false;
        if (cursor.moveToFirst()) {
            exist = true;
        }
        cursor.close();
        return exist;
    }
}
