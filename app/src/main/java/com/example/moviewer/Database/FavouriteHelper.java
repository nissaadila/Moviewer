package com.example.moviewer.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

    public Movie authMovie(String title){
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM favourite WHERE title = ?",
                new String[]{title});

        Movie fav = null;
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            fav = new Movie();
            fav.setId(cursor.getInt(0));
            fav.setTitle(cursor.getString(1));
            fav.setOverview(cursor.getString(2));
            fav.setPath(cursor.getString(3));
            fav.setRating(cursor.getString(4));
            fav.setReleaseDate(cursor.getString(5));
            cursor.close();
        }
        db.close();
        return fav;
    }

    public void deleteFavourite(Movie favourite){
        db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, "title = ?", new String[]{favourite.getTitle() + ""});
        db.close();
    }

}
