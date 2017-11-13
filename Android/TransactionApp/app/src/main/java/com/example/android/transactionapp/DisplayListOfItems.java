package com.example.android.transactionapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import models.Transaction;

public class DisplayListOfItems extends AppCompatActivity {

    ArrayList<Transaction> listItems = new ArrayList<Transaction>();
    int index=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_of_items);
        ListView lv = (ListView) findViewById(R.id.listView);
        Transaction t1 = new Transaction("Wages and Salaries", "Income", 5000);
        Transaction t2 = new Transaction("Scholarship", "Expense", 1000);
        Transaction t3 = new Transaction("Food", "Expense", 100);
        addTransaction(t1);
        addTransaction(t2);
        addTransaction(t3);
        /*Intent i = getIntent();
        if (i.getParcelableExtra("newTransaction")!=null)
        {
            final Transaction transaction = (Transaction) i.getParcelableExtra("newTransaction");
            listItems.add(transaction);
        }*/

        ArrayAdapter<Transaction> adapter = new ArrayAdapter<Transaction>(this, android.R.layout.simple_list_item_1, listItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(DisplayListOfItems.this, EditItem.class);
                i.putExtra("item", (Transaction) parent.getItemAtPosition(position));
                startActivityForResult(i, 1);
            }
        });

    }

    public void addTransaction(Transaction tr){
        index++;
        tr.setId(index);
        listItems.add(tr);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        {
            switch(requestCode) {
                case (1): {
                    if (resultCode == Activity.RESULT_OK) {
                        Transaction tr = data.getParcelableExtra("result");
                        ListView lv = (ListView) findViewById(R.id.listView);
                        for (int i = 0; i < listItems.size(); i++) {
                            if (listItems.get(i).getId() == tr.getId())
                                listItems.set(i, tr);
                        }
                        ArrayAdapter<Transaction> adapter = new ArrayAdapter<Transaction>(this, android.R.layout.simple_list_item_1, listItems);
                        lv.setAdapter(adapter);
                    }
                    break;
                }
            }
        }
    }
}