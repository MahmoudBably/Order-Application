package com.example.mohamed.ordersapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.TooltipCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {

    private EditText mEditTextId;
    private Button help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEditTextId = findViewById(R.id.editText_id);
        help = findViewById(R.id.help_button);

        mEditTextId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    help.setBackgroundResource(R.drawable.ic_action_close);
                }
                else {
                    help.setBackgroundResource(R.drawable.ic_action_help);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditTextId.getText().toString().trim().length() == 0) {
                    TooltipCompat.setTooltipText(v, "Enter the ID of the order you'd like to delete!");
                }
                else{
                    mEditTextId.setText("");
                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public void DelteAllItem(View view) {

        DBFunctions db = new DBFunctions(this);
        ArrayList<String> data = db.getAllItems();
        db.DelteAllItem();

        Toast.makeText(getApplicationContext(), "Orders deleted Successfully!",
                Toast.LENGTH_LONG).show();

        Intent i = new Intent(DeleteActivity.this, ShowActivity.class);
        startActivity(i);
        finish();
    }

    public void DelteItem(View view) {
        DBFunctions db = new DBFunctions(this);
        boolean idValidation;

        if(mEditTextId.getText().toString().trim().length() == 0){
            Toast.makeText(getApplicationContext(), "ERROR! Missing fields",
                    Toast.LENGTH_LONG).show();
        }
        else{

            idValidation = db.checkID(mEditTextId.getText().toString());
            if(idValidation == false){
                Toast.makeText(getApplicationContext(), "ERROR! ID is not exist.",
                        Toast.LENGTH_LONG).show();
            }
            else{
                ArrayList<String> data = db.getAllItems();
                db.DelteItem(Integer.parseInt(mEditTextId.getText().toString()));

                Toast.makeText(getApplicationContext(), R.string.order_deleted,
                        Toast.LENGTH_LONG).show();

                Intent i = new Intent(DeleteActivity.this, ShowActivity.class);
                startActivity(i);
                finish();
            }
        }
    }
}
