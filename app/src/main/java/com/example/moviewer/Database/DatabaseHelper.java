package com.example.moviewer.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final String CREATE_TABLE_USERS = "CREATE TABLE users(id integer PRIMARY KEY AUTOINCREMENT," +
                                                "username text NOT NULL," +
                                                "password text NOT NULL," +
                                                "email text NOT NULL)";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
    }
}
