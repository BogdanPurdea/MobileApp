package com.example.android.transactionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText categoryEditText = findViewById(R.id.categoryEditText);
                EditText typeEditText = findViewById(R.id.typeEditText);
                EditText valueEditText = findViewById(R.id.valueEditText);

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ "bogdanpurdea@yahoo.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Add transaction");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "TransactionDto\nCategory: "+categoryEditText.getText().toString()+
                        "\nType: "+typeEditText.getText().toString()+"\nValue: "+valueEditText.getText().toString());

                emailIntent.setType("message/rfc822");

                startActivity(Intent.createChooser(emailIntent,"Send email..."));
            }
        });
        final Button showButton = findViewById(R.id.showButton);
        showButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DisplayListOfItems.class);
                startActivity(i);
            }
        });
    }
}
