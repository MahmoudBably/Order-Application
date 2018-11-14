package com.example.mohamed.ordersapp;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    private ListView ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ls = findViewById(R.id.showItems);


        DBFunctions db = new DBFunctions(this);
        ArrayList<String> data = db.getAllItems();
        ls.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1 , data));
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivityForResult(myIntent, 0);
        finish();
        return true;

    }

}
