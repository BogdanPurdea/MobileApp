package com.example.android.transactionapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import models.Transaction;

public class EditItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Intent i = getIntent();
        final Transaction transaction = (Transaction)i.getParcelableExtra("item");
        TextView transactionTextView = (TextView)findViewById(R.id.transactionTextView);
        transactionTextView.setText("Transaction "+transaction.getIdString());
        final EditText categoryEditText = (EditText)findViewById(R.id.categoryEditText);
        categoryEditText.setText(transaction.getCategoryString());
        final EditText typeEditText = (EditText)findViewById(R.id.typeEditText);
        typeEditText.setText(transaction.getTypeString());
        final EditText valueEditText = (EditText)findViewById(R.id.valueEditText);
        valueEditText.setText(transaction.getValueString());
        Button showButton = (Button) findViewById(R.id.submitButton);
        showButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                transaction.setCategory(categoryEditText.getText().toString());
                transaction.setType(typeEditText.getText().toString());
                transaction.setValue(Integer.parseInt(valueEditText.getText().toString()));
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", transaction);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
