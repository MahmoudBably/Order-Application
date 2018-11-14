package com.example.mohamed.ordersapp;

import android.provider.BaseColumns;

/**
 * Created by Mohamed on 4/9/2018.
 */

public class DBContent {

    private DBContent(){}

    public static final class DBAttributes implements BaseColumns{
        public static final String TABLE_NAME = "OrderList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
