package com.example.final_project.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS msuser" +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "username VARCHAR(255)," +
                        "password VARCHAR(255))"
        );

        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS trwatchlist" +
                        "(user_id INTEGER NOT NULL," +
                        "track_id INTEGER NOT NULL," +
                        "PRIMARY KEY (user_id, track_id)," +
                        "FOREIGN KEY (user_id) REFERENCES msuser (id))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS msuser");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS trwatchlist");
        onCreate(sqLiteDatabase);
    }
}
