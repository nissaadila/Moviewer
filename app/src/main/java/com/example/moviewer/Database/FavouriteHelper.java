package com.example.moviewer.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.moviewer.Models.User;
import com.example.moviewer.Movie;

import java.util.Vector;

public class FavouriteHelper {

    private final String TABLE_NAME = "favourite";
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public FavouriteHelper(Context context){
        dbHelper = new DatabaseHelper(context);
    }

    public Vector<Movie> getFavouriterData(int id){
        db = dbHelper.getReadableDatabase();
        Vector<Movie> movieList = new Vector<>();
        String query = "SELECT * FROM favourite";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        Movie temp;
        int temp_id;
        String temp_title;
        String temp_overview;
        String temp_path;
        String temp_rating;
        String temp_releaseDate;
        if(cursor.getCount() > 0){
            do {
                temp_id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                if (temp_id == id){
                    temp_title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                    temp_overview = cursor.getString(cursor.getColumnIndexOrThrow("overview"));
                    temp_path = cursor.getString(cursor.getColumnIndexOrThrow("path"));
                    temp_rating = cursor.getString(cursor.getColumnIndexOrThrow("rating"));
                    temp_releaseDate = cursor.getString(cursor.getColumnIndexOrThrow("releaseDate"));
                    temp = new Movie(id, temp_title, temp_overview, temp_path, temp_rating, temp_releaseDate);
                    movieList.add(temp);
                }
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return movieList;
    }

    public void insertFavourite(Movie favourite){
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", favourite.getId());
        contentValues.put("title", favourite.getTitle());
        contentValues.put("overview", favourite.getOverview());
        contentValues.put("path", favourite.getPath());
        contentValues.put("rating", favourite.getRating());
        contentValues.put("releaseDate", favourite.getReleaseDate());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public Movie authMovie(String title, int id){
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM favourite WHERE title = ? and id = ?",
                new String[]{title, id + ""});

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
        db.delete(TABLE_NAME, "title = ? and id = ?", new String[]{favourite.getTitle() + "", favourite.getId() + ""});
        db.close();
    }

}
