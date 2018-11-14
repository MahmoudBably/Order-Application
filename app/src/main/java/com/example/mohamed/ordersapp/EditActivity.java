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

public class EditActivity extends AppCompatActivity {

    private EditText mEditTextName;
    private TextView mTextViewAmount;
    private EditText mEditTextId;
    private int mAmount = 0;
    private Button help, help2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEditTextName = findViewById(R.id.editText_order);
        mTextViewAmount = findViewById(R.id.textview_amount);
        mEditTextId = findViewById(R.id.editText_id);

        help = findViewById(R.id.help_button);
        help2 = findViewById(R.id.help_button1);

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
                    TooltipCompat.setTooltipText(v, "Enter the ID of the order you'd like to edit!");
                }
                else{
                    mEditTextId.setText("");
                }
            }
        });


        mEditTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    help2.setBackgroundResource(R.drawable.ic_action_close);
                }
                else {
                    help2.setBackgroundResource(R.drawable.ic_action_help);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        help2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditTextName.getText().toString().trim().length() == 0) {
                    TooltipCompat.setTooltipText(v, "Enter the new order you'd like to change!");
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

    public void EditItem(View view) {

        DBFunctions db = new DBFunctions(this);
        boolean idValidation = true;


        if(mEditTextName.getText().toString().trim().length() == 0 || mAmount == 0 || mEditTextId.getText().toString().trim().length() == 0){
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
                db.EditItem(mEditTextName.getText().toString(), mAmount, Integer.parseInt(mEditTextId.getText().toString()));

                Toast.makeText(getApplicationContext(), R.string.order_edited,
                        Toast.LENGTH_LONG).show();

                Intent i = new Intent(EditActivity.this, ShowActivity.class);
                startActivity(i);
                finish();
            }

        }
    }
}
