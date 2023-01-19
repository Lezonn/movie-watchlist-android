package com.example.final_project.db;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class WatchlistFunctionDB {
    private final DBHelper dbHelper;
    private final String TABLE_NAME = "trwatchlist";
    private final String DB_NAME = "DB-kelompok8";

    public WatchlistFunctionDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, 1);
    }

    public ArrayList<Integer> getUserWatchlist(Integer userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] args = new String[] { userId.toString() };
        Cursor cursor = db.rawQuery("SELECT * FROM trwatchlist WHERE user_id=?", args);

        ArrayList<Integer> foundWatchlist = new ArrayList<>();

        while(cursor.moveToNext()) {
            Integer trackId = cursor.getInt(cursor.getColumnIndex("track_id"));
            foundWatchlist.add(trackId);
        }

        cursor.close();
        return foundWatchlist;
    }

    public boolean checkWatchlist(Integer userId, Integer trackId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] args = new String[] { userId.toString(), trackId.toString() };
        Cursor cursor = db.rawQuery("SELECT * FROM trwatchlist WHERE user_id=? AND track_id=?", args);

        if(cursor.moveToNext()) {
            cursor.close();
            return true;
        }

        return false;
    }


    public void addWatchlist(Integer userId, Integer trackId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user_id", userId);
        cv.put("track_id", trackId);
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    public void removeWatchlist(Integer userId, Integer trackId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] args = {"" + userId, "" + trackId};
        db.delete(TABLE_NAME, "user_id=? AND track_id=?", args);
        db.close();
    }
}
