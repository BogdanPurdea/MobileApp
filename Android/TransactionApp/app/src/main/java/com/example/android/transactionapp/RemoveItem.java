package com.example.android.transactionapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by Bogdan on 12/12/2017.
 */

public class RemoveItem extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);
        final EditText idEditText = (EditText)findViewById(R.id.idEditText);
        idEditText.setText("");
        Button showButton = (Button) findViewById(R.id.submitButton);
        showButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                final String id = idEditText.getText().toString();
                resultIntent.putExtra("resultId", id);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}