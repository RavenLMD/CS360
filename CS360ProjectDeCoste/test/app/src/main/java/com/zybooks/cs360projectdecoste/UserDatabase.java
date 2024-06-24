package com.zybooks.cs360projectdecoste;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int VERSION = 1;

    public UserDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    //All values in table, including table name
    private final class UserTable {
        private static final String TABLE = "users";
        private static final String COL_USERNAME = "username";
        private static final String COL_PASSWORD = "password";
    }

    //Creates 2 column table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + UserTable.TABLE + " (" + UserTable.COL_USERNAME + " text, " +
                UserTable.COL_PASSWORD + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Recreates table
        db.execSQL("drop table if exists " + UserTable.TABLE);
        onCreate(db);
    }

    public void addUser(String username, String password) {
        //Grabs database
        SQLiteDatabase db = getWritableDatabase();

        //Creates a store for the values
        ContentValues values = new ContentValues();

        //Adds username and password to database
        values.put(UserTable.COL_USERNAME, username);
        values.put(UserTable.COL_PASSWORD, password);
        db.insert(UserTable.TABLE, null, values);
    }



    public boolean checkPasswordByUsername(String username, String password) {
        //Grabs database
        SQLiteDatabase db = getReadableDatabase();

        //SQL command
        String sql = "select * from " + UserTable.TABLE + " where rating = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{username});
        //Checks if password is linked to the given username
        if (cursor.moveToFirst()) {
            do {
                //Gets password from database
                String passwordCheck= cursor.getString(1);
                if(passwordCheck == password) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }
}