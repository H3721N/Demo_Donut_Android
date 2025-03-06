package com.gomez.herlin.logindemo.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginService extends SQLiteOpenHelper {
    public LoginService(Context context) {
        super(context, "LoginDemo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " username TEXT, password TEXT)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", "hgomez123");
        contentValues.put("password", "hgomez123");
        db.insert("users", null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
    }

    public boolean Insert(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = sqLiteDatabase.insert("users", null, contentValues);
        return result != -1;
    }

    public Boolean verifyUser(String username) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE username=?", new String[]{username});
        int count = cursor.getCount();
        cursor.close();
        return count <= 0;
    }

    public Boolean verifyLogin(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{username, password});
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }
}