package com.example.android.transactionapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Bogdan on 12/11/2017.
 */

public class AddItem extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        final String income = "Income";
        final String expense = "Expense";
        final EditText categoryEditText = findViewById(R.id.categoryEditText);
        categoryEditText.setText("");
        final EditText typeEditText = findViewById(R.id.typeEditText);
        typeEditText.setText("");
        typeEditText.setKeyListener(null);
        final EditText valueEditText = findViewById(R.id.valueEditText);
        valueEditText.setText("");
        Button showButton =  findViewById(R.id.submitButton);
        showButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("resultCategory", categoryEditText.getText().toString());
                resultIntent.putExtra("resultType", typeEditText.getText().toString());
                resultIntent.putExtra("resultValue", valueEditText.getText().toString());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
        Button typeButton = findViewById(R.id.typeButton);
        typeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddItem.this);

                alertDialogBuilder.setTitle("Choose a type");

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton(income,new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                typeEditText.setText(income);
                                dialog.cancel();
                            }
                        })
                        .setNeutralButton(expense,new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                typeEditText.setText(expense);
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();

                alertDialog.show();
            }
        });
    }
}
