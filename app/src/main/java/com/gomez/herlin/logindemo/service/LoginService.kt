package com.gomez.herlin.logindemo.service

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class LoginService(context: Context?) :
    SQLiteOpenHelper(context, "LoginDemo", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " username TEXT, password TEXT)"
        )
        val contentValues = ContentValues()
        contentValues.put("username", "hgomez123")
        contentValues.put("password", "hgomez123")
        db.insert("users", null, contentValues)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS users")
    }

    fun Insert(username: String?, password: String?): Boolean {
        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        val result = sqLiteDatabase.insert("users", null, contentValues)
        return result != -1L
    }

    fun verifyUser(username: String): Boolean {
        val sqLiteDatabase = this.writableDatabase
        val cursor =
            sqLiteDatabase.rawQuery("SELECT * FROM users WHERE username=?", arrayOf(username))
        val count = cursor.count
        cursor.close()
        return count <= 0
    }

    fun verifyLogin(username: String, password: String): Boolean {
        val sqLiteDatabase = this.readableDatabase
        val cursor = sqLiteDatabase.rawQuery(
            "SELECT * FROM users WHERE username=? AND password=?",
            arrayOf(username, password)
        )
        val count = cursor.count
        cursor.close()
        return count > 0
    }
}