package com.example.android.transactionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import models.Transaction;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            EditText categoryEditText = (EditText)findViewById(R.id.categoryEditText);
            EditText typeEditText = (EditText)findViewById(R.id.typeEditText);
            EditText valueEditText = (EditText)findViewById(R.id.valueEditText);

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ "bogdanpurdea@yahoo.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Add transaction");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Transaction\nCategory: "+categoryEditText.getText().toString()+
            "\nType: "+typeEditText.getText().toString()+"\nValue: "+valueEditText.getText().toString());

            emailIntent.setType("message/rfc822");

            startActivity(Intent.createChooser(emailIntent,"Send email..."));
            }
        });
        final Button showButton = (Button) findViewById(R.id.showButton);
        showButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            EditText categoryEditText = (EditText)findViewById(R.id.categoryEditText);
            EditText typeEditText = (EditText)findViewById(R.id.typeEditText);
            EditText valueEditText = (EditText)findViewById(R.id.valueEditText);
            Intent i = new Intent(MainActivity.this, DisplayListOfItems.class);
            if(categoryEditText.getText() != null & typeEditText.getText() != null & valueEditText.getText() != null)
            {
                Transaction t = new Transaction(typeEditText.getText().toString(),
                            valueEditText.getText().toString(), Integer.parseInt(valueEditText.getText().toString()));
                i.putExtra("newTransaction", t);
            }
            startActivity(i);
            }
        });
    }
}
