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

public class AddActivity extends AppCompatActivity {

    private EditText mEditTextName;
    private TextView mTextViewAmount;
    private int mAmount = 0;
    private Button help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEditTextName = findViewById(R.id.editText_order);
        mTextViewAmount = findViewById(R.id.textview_amount);

        help = findViewById(R.id.help_button1);

        mEditTextName.addTextChangedListener(new TextWatcher() {
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
                if (mEditTextName.getText().toString().trim().length() == 0) {
                    TooltipCompat.setTooltipText(v, "Enter the order you'd like to add!");
                }
                else{
                    mEditTextName.setText("");
                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public void DecreaseAmount(View view) {
        if(mAmount > 0){
            mAmount--;
            mTextViewAmount.setText(String.valueOf(mAmount));
        }
    }

    public void IncreaseAmount(View view) {
        mAmount++;
        mTextViewAmount.setText(String.valueOf(mAmount));
    }

    public void AddItem(View view) {

        if(mEditTextName.getText().toString().trim().length() == 0 || mAmount == 0){
            Toast.makeText(getApplicationContext(), "ERROR! Missing fields",
                    Toast.LENGTH_LONG).show();
        }
        else{
            DBFunctions db = new DBFunctions(this);
            db.InsertItems(mEditTextName,mAmount);


            Toast.makeText(getApplicationContext(), R.string.order_added,
                    Toast.LENGTH_LONG).show();

            Intent i = new Intent(AddActivity.this, ShowActivity.class);
            startActivity(i);
            finish();
        }

    }
}
