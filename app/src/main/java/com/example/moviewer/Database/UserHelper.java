package com.example.moviewer.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.moviewer.Models.User;

public class UserHelper {

    private final String TABLE_NAME = "users";
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public UserHelper(Context context){
        dbHelper = new DatabaseHelper(context);
    }

    //get
    public Cursor getUserData(){
        db = dbHelper.getWritableDatabase();
        return db.rawQuery("SELECT * FROM users", null);
    }

    // insert
    public void insert(User user){
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.getUsername());
        contentValues.put("password", user.getPassword());
        contentValues.put("email", user.getEmail());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    // auth -> read
    public User authUsername(String username){
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ?",
                new String[]{username});

        User user = null;
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            user = new User();
            user.setId(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            cursor.close();
        }
        db.close();
        return user;
    }

    // auth -> read
    public User auth(String email, String password){
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ? and password = ?",
                new String[]{email, password});

        User user = null;
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            user = new User();
            user.setId(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            cursor.close();
        }
        db.close();
        return user;
    }

    public void update(User user){
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.getUsername());
        contentValues.put("email", user.getEmail());
        db.update(TABLE_NAME, contentValues, "id = ?", new String[]{user.getId() + ""});
        db.close();
    }

    public void delete(User user){
        db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[]{user.getId() + ""});
        db.close();
    }
}
