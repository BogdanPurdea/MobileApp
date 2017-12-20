package com.example.android.transactionapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Intent i = getIntent();
        final String id = i.getStringExtra("id");
        String category = i.getStringExtra("category");
        String type = i.getStringExtra("type");
        String value = i.getStringExtra("value");

        TextView transactionTextView = findViewById(R.id.transactionTextView);
        transactionTextView.setText("Transaction "+ id);
        final EditText categoryEditText = findViewById(R.id.categoryEditText);
        categoryEditText.setText(category);
        final EditText typeEditText = findViewById(R.id.typeEditText);
        typeEditText.setText(type);
        final EditText valueEditText = findViewById(R.id.valueEditText);
        valueEditText.setText(value);
        Button showButton = findViewById(R.id.submitButton);
        showButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("resultId", id);
                resultIntent.putExtra("resultCategory", categoryEditText.getText().toString());
                resultIntent.putExtra("resultType", typeEditText.getText().toString());
                resultIntent.putExtra("resultValue", valueEditText.getText().toString());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
