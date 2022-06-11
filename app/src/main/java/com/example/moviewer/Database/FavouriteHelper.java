package com.example.moviewer.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.moviewer.Models.User;
import com.example.moviewer.Movie;

public class FavouriteHelper {

    private final String TABLE_NAME = "favourite";
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public FavouriteHelper(Context context){
        dbHelper = new DatabaseHelper(context);
    }

    public Cursor getFavouriterData(){
        db = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery("SELECT * FROM favourite", null);
        }
        return cursor;
    }

    public void insertFavourite(Movie favourite){
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", favourite.getTitle());
        contentValues.put("overview", favourite.getOverview());
        contentValues.put("path", favourite.getPath());
        contentValues.put("rating", favourite.getRating());
        contentValues.put("releaseDate", favourite.getReleaseDate());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public void deleteFavourite(Movie favourite){
        db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[]{favourite.getId() + ""});
        db.close();
    }

}
