package com.example.mohamed.ordersapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mohamed.ordersapp.DBContent.*;

/**
 * Created by Mohamed on 4/9/2018.
 */

public class DBConnection extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "OrderList.dp";
    public static final int DATABASE_VERSION = 1;

    public DBConnection(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE " + DBAttributes.TABLE_NAME + " (" +
                DBAttributes._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBAttributes.COLUMN_NAME + " TEXT NOT NULL, " +
                DBAttributes.COLUMN_AMOUNT + " INTEGER NOT NULL, " +
                DBAttributes.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        sqLiteDatabase.execSQL(SQL_CREATE_GROCERYLIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBAttributes.COLUMN_NAME);
        onCreate(sqLiteDatabase);
    }
}
