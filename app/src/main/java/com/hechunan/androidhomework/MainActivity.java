package com.hechunan.androidhomework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SearchLayout searchLayout = findViewById(R.id.search_layout);
        EditText searchValue = findViewById(R.id.search_value);
        Button cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchValue.setText("");
            }
        });
        Log.d("Main", "finish layout init");
        initItems();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ItemViewAdapter itemViewAdapter = new ItemViewAdapter(items);
        recyclerView.setAdapter(itemViewAdapter);
        searchLayout.setOnEditSearchViewListener(new SearchLayout.OnEditSearchViewListener() {
            @Override
            public void afterChanged(String text) {
                List<String> tmpItemLists = new ArrayList<>();
                for (String item : items) {
                    if (item.contains(text)) {
                        tmpItemLists.add(item);
                    }
                }
                itemViewAdapter.dataChangedHandler(tmpItemLists);
            }
        });
    }

    private void initItems() {
        for (int i = 0; i  < 10000; ++i) {
            items.add("学生"+String.valueOf(i)+"号");
        }
    }
}
