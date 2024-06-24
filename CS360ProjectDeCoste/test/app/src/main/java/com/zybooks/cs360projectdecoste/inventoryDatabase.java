package com.zybooks.cs360projectdecoste;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class inventoryDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "items.db";
    private static final int VERSION = 1;

    public inventoryDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    //All values in table, including table name
    private final class UserTable {
        private static final String TABLE = "users";
        private static final String COL_ITEM = "username";
        private static final String COL_ITEMCOUNT = "password";
    }

    //Creates 2 column table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + UserTable.TABLE + " (" + UserTable.COL_ITEM + " text, " +
                UserTable.COL_ITEMCOUNT + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Recreates table
        db.execSQL("drop table if exists " + UserTable.TABLE);
        onCreate(db);
    }

    public void addItem(String item) {
        //Grabs database
        SQLiteDatabase db = getWritableDatabase();

        //Creates a store for the values
        ContentValues values = new ContentValues();

        //Adds username and password to database
        values.put(UserTable.COL_ITEM, item);
        values.put(UserTable.COL_ITEMCOUNT, "0");
        db.insert(UserTable.TABLE, null, values);
    }
}