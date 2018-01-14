package com.example.android.transactionapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import models.AppDatabase;
import models.Category;
import models.Repo;

/**
 * Created by Bogdan on 1/5/2018.
 */

public class DisplayCategories extends AppCompatActivity {
    List<Category> listItems = new ArrayList<>();
    Repo repo = new Repo(this);
    AppDatabase db = repo.getDb();
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_list_of_items);

        Intent i = getIntent();
        userId = i.getStringExtra("userId");

        listItems = db.categoryDao().getAll();
        ListView lv = findViewById(R.id.listView);
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        lv.setAdapter(adapter);
        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(DisplayCategories.this, EditItem.class);
                Transaction tr = (Transaction) parent.getItemAtPosition(position);
                i.putExtra("id", tr.id.toString());
                String categoryName = db.categoryDao().findById(tr.categoryid).getName();
                i.putExtra("category", categoryName);
                i.putExtra("type", tr.type);
                i.putExtra("value", tr.value.toString());
                startActivityForResult(i, 1);
            }
        });*/
        final Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(DisplayCategories.this, AddCategory.class);
                startActivityForResult(i, 1);
            }
        });
        final Button removeButton = findViewById(R.id.removeButton);
        removeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(DisplayCategories.this, RemoveItem.class);
                startActivityForResult(i, 2);
            }
        });
    }


    public void addCategory(String categoryName){
        Integer index = db.categoryDao().getLastUsedId();
        Category category = new Category();
        if (index == null)
            index=1;
        else
            index++;
        category.setId(index);
        category.setName(categoryName);
        db.categoryDao().insertOne(category);
    }
    public void removeCategory(Integer id){
        Category category = db.categoryDao().findById(id);
        db.categoryDao().delete(category);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        {
            switch (requestCode) {
                case (1): {
                    if (resultCode == Activity.RESULT_OK) {
                        String categoryName= data.getStringExtra("resultCategoryName");
                        ListView lv = findViewById(R.id.listView);
                        addCategory(categoryName);
                        listItems = db.categoryDao().getAll();
                        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
                        lv.setAdapter(adapter);
                    }
                    break;
                }
                case (2): {
                    if (resultCode == Activity.RESULT_OK) {
                        int id = Integer.valueOf(data.getStringExtra("resultId"));
                        ListView lv = findViewById(R.id.listView);
                        removeCategory(id);
                        listItems = db.categoryDao().getAll();
                        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
                        lv.setAdapter(adapter);
                    }
                    break;
                }
            }
        }
    }
}