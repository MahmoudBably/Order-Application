package com.example.mohamed.ordersapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Mohamed on 4/9/2018.
 */

public class DBFunctions extends DBConnection {
    public DBFunctions(Context context) {
        super(context);
    }

    public void InsertItems (EditText ItemName, int mAmount){
        if(ItemName.getText().toString().trim().length() == 0 || mAmount == 0){
            return;
        }

        String name = ItemName.getText().toString();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBContent.DBAttributes.COLUMN_NAME, name);
        contentValues.put(DBContent.DBAttributes.COLUMN_AMOUNT, mAmount);

        db.insert(DBContent.DBAttributes.TABLE_NAME,null,contentValues);

        ItemName.getText().clear();

    }


    public ArrayList getAllItems(){

        ArrayList Data = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+ DBContent.DBAttributes.TABLE_NAME+" Order BY "+ DBContent.DBAttributes.COLUMN_TIMESTAMP+ " DESC",null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            Data.add("\nID: "+res.getString(res.getColumnIndex(DBContent.DBAttributes._ID))+ "\nDescription: "+res.getString(res.getColumnIndex(DBContent.DBAttributes.COLUMN_NAME))+"\nQuantity: "+res.getString(res.getColumnIndex(DBContent.DBAttributes.COLUMN_AMOUNT))+"\n");
            res.moveToNext();
        }
        return Data;
    }

    public boolean checkID(String i){
        int id = Integer.parseInt(i);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+ DBContent.DBAttributes.TABLE_NAME+" Order BY "+ DBContent.DBAttributes.COLUMN_TIMESTAMP+ " DESC",null);
        res.moveToFirst();
        int currentId;
        while (res.isAfterLast() == false){
            currentId = Integer.parseInt(res.getString(res.getColumnIndex(DBContent.DBAttributes._ID)));
            if(currentId == id){
                return true;
            }
            res.moveToNext();
        }
        return false;
    }

    public void DelteItem(Integer id){

        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("delete from "+DBContent.DBAttributes.TABLE_NAME+" where "+DBContent.DBAttributes._ID+"="+Integer.toString(id));
        db.delete(DBContent.DBAttributes.TABLE_NAME,
                DBContent.DBAttributes._ID + "=" + Integer.toString(id),null);

    }

    public void DelteAllItem(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBContent.DBAttributes.TABLE_NAME,
                null,null);

    }

    public void EditItem(String ItemName ,int Amount,Integer ID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContent.DBAttributes.COLUMN_NAME, ItemName);
        contentValues.put(DBContent.DBAttributes.COLUMN_AMOUNT, Amount);
        db.update(DBContent.DBAttributes.TABLE_NAME,contentValues, DBContent.DBAttributes._ID+" = ?", new String[] {Integer.toString(ID)});
    }
}
