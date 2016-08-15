package com.noisyz.noisybindexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.noisyz.databindinglibrary.bind.base.impl.adapter.BindAdapter;
import com.noisyz.databindinglibrary.bind.base.impl.adapter.recyclerview.RecyclerBindAdapter;
import com.noisyz.noisybindexample.item.ExampleListItem;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_activity_layout);
        ArrayList<ExampleListItem> itemList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            itemList.add(new ExampleListItem("Title " + i, "Subtitle " + i, i % 2 == 0));
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new RecyclerBindAdapter<>(itemList, R.layout.example_list_item_layout));
        }
    }
}
