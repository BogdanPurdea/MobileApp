package com.example.android.transactionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SubmitDetails extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_details);
        final Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText infoEditText = findViewById(R.id.infoEditText);

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ "bogdanpurdea@yahoo.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Add transaction");
                emailIntent.putExtra(Intent.EXTRA_TEXT, infoEditText.getText().toString());

                emailIntent.setType("message/rfc822");

                startActivity(Intent.createChooser(emailIntent,"Send email..."));
            }
        });
    }
}
