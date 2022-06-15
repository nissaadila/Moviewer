package com.example.moviewer.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery("SELECT * FROM users", null);
        }
        return cursor;
    }

    public User getUserData(int idx) {
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE id = " + idx, null);

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

    public User checkEmail(String email) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ?",
                new String[]{email});

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

    public User findUser(String email, String password) {
        User curr_user = null;
        String query = "SELECT * FROM users";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        String tempUsername, tempEmail, tempPassword;

        if (cursor.getCount() > 0){
            do {
                tempEmail = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                tempPassword = cursor.getString(cursor.getColumnIndexOrThrow("password"));
                if (tempEmail.equals(email) && tempPassword.equals(password)){
                    tempUsername = cursor.getString(cursor.getColumnIndexOrThrow("username"));
                    curr_user = new User(tempUsername, tempPassword, tempEmail);
                }
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return curr_user;
    }

    public boolean updateUsername(int id, String newUsername){
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", newUsername);

        int update = db.update("users", contentValues, "id = ?", new String[] {String.valueOf(id)});

        if(update == 0)
            return false;
        else
            return true;
    }

    public boolean updateEmail(int id, String newEmail){
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", newEmail);

        int update = db.update("users", contentValues, "id = ?", new String[] {String.valueOf(id)});

        if(update == 0)
            return false;
        else
            return true;
    }

    public void delete(User user){
        db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[]{user.getId() + ""});
        db.close();
    }
}
