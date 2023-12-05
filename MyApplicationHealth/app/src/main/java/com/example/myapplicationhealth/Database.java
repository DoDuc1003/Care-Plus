package com.example.myapplicationhealth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "create table users(username text,email text,password text)";
        db.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void register(String username, String email, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, contentValues);
        db.close();
    }

    public int login(String username, String password) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery("select * from users where username=? and password=?", str);
        if(c.moveToFirst()) {
            result = 1;
        }
        database.close();

        return result;
    }
}
