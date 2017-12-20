package com.example.android.transactionapp;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import models.AppDatabase;
import models.Transaction;

public class DisplayListOfItems extends AppCompatActivity {
    List<Transaction> listItems = new ArrayList<>();
    AppDatabase db = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_list_of_items);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database").allowMainThreadQueries().build();

        listItems = db.transactionDao().getAll();
        ListView lv = findViewById(R.id.listView);
        ArrayAdapter<Transaction> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(DisplayListOfItems.this, EditItem.class);
                Transaction tr = (Transaction) parent.getItemAtPosition(position);
                i.putExtra("id", tr.id.toString());
                i.putExtra("category", tr.category);
                i.putExtra("type", tr.type);
                i.putExtra("value", tr.value.toString());
                startActivityForResult(i, 1);
            }
        });
        final Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(DisplayListOfItems.this, AddItem.class);
                startActivityForResult(i, 2);
            }
        });
        final Button removeButton = findViewById(R.id.removeButton);
        removeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(DisplayListOfItems.this, RemoveItem.class);
                startActivityForResult(i, 3);
            }
        });
    }
    public void addTransaction(String category, String type, Integer value){
        Integer index = db.transactionDao().getLastUsedId();
        Transaction tr = new Transaction();
        index++;
        tr.setId(index);
        tr.setCategory(category);
        tr.setType(type);
        tr.setValue(value);
        db.transactionDao().insertOne(tr);
    }
    public void removeTransaction(Integer id){
        Transaction tr = db.transactionDao().findById(id);
        db.transactionDao().delete(tr);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        {
            switch (requestCode) {
                case (1): {
                    if (resultCode == Activity.RESULT_OK) {

                        Integer id = Integer.parseInt(data.getStringExtra("resultId"));
                        String category = data.getStringExtra("resultCategory");
                        String type = data.getStringExtra("resultType");
                        Integer value = Integer.parseInt(data.getStringExtra("resultValue"));
                        ListView lv = findViewById(R.id.listView);
                        db.transactionDao().updateTransaction(id, category, type, value);
                        listItems = db.transactionDao().getAll();
                        ArrayAdapter<Transaction> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
                        lv.setAdapter(adapter);
                    }
                    break;

                }
                case (2): {
                    if (resultCode == Activity.RESULT_OK) {
                        String category = data.getStringExtra("resultCategory");
                        String type = data.getStringExtra("resultType");
                        Integer value = Integer.parseInt(data.getStringExtra("resultValue"));
                        ListView lv = findViewById(R.id.listView);
                        addTransaction(category, type, value);
                        listItems = db.transactionDao().getAll();
                        ArrayAdapter<Transaction> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
                        lv.setAdapter(adapter);
                    }
                    break;
                }
                case (3): {
                    if (resultCode == Activity.RESULT_OK) {
                        int id = Integer.valueOf(data.getStringExtra("resultId"));
                        ListView lv = findViewById(R.id.listView);
                        removeTransaction(id);
                        listItems = db.transactionDao().getAll();
                        ArrayAdapter<Transaction> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
                        lv.setAdapter(adapter);
                    }
                }
                break;
            }
        }
    }
}