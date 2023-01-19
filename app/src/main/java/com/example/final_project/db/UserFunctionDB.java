package com.example.final_project.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.final_project.model.User;

public class UserFunctionDB {
    private final DBHelper dbHelper;
    private final String TABLE_NAME = "msuser";
    private final String DB_NAME = "DB-kelompok8";


    public UserFunctionDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, 1);
    }

    public void register(String username, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

//    public void deleteUser(long id) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        String[] args = {"" + id};
//        db.delete(TABLE_NAME, "id=?", args);
//        db.close();
//    }

    public boolean checkUsername(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] args = new String[] { username };
        Cursor cursor = db.rawQuery("SELECT * FROM msuser WHERE username = ? LIMIT 1", args);

        if(cursor.moveToNext()) {
            cursor.close();
            return true;
        }

        return false;
    }

    public User getUser(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] args = new String[] { username, password };
        Cursor cursor = db.rawQuery("SELECT * FROM msuser WHERE username = ? AND password = ? LIMIT 1", args);

        User foundUser = null;

        if(cursor.moveToNext()) {
            foundUser = new User();
            foundUser.setId(cursor.getInt(cursor.getColumnIndex("id")));
            foundUser.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            foundUser.setPassword( cursor.getString(cursor.getColumnIndex("password")));
        }

        cursor.close();
        return foundUser;
    }
}
