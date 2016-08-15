package com.noisyz.noisybindexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.noisyz.databindinglibrary.bind.base.impl.adapter.BindAdapter;
import com.noisyz.noisybindexample.item.ExampleListItem;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<ExampleListItem> itemList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            itemList.add(new ExampleListItem("Title " + i, "Subtitle " + i, i % 2 == 0));
        }
        ((ListView) findViewById(R.id.listView)).setAdapter(new BindAdapter<>(itemList, R.layout.example_list_item_layout));
    }
}
