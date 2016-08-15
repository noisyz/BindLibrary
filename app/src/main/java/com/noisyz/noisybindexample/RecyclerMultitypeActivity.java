package com.noisyz.noisybindexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.noisyz.databindinglibrary.bind.base.impl.adapter.recyclerview.RecyclerBindAdapter;
import com.noisyz.databindinglibrary.callback.layoutcallback.LayoutResourceProvider;
import com.noisyz.noisybindexample.item.ExampleListItem;

import java.util.ArrayList;

public class RecyclerMultitypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_activity_layout);
        ArrayList<ExampleListItem> itemList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            itemList.add(new ExampleListItem("Title " + i, "Subtitle " + i, i % 2 == 0, i%3));
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new RecyclerBindAdapter<>(itemList, new LayoutResourceProvider() {
                @Override
                public int getLayoutResourceID(int type) {
                    switch (type) {
                        case ExampleListItem.RANDOM_TYPE_1:
                            return R.layout.example_list_item_layout;
                        case ExampleListItem.RANDOM_TYPE_2:
                            return R.layout.example_list_item_layout_2;
                        case ExampleListItem.RANDOM_TYPE_3:
                            return R.layout.example_list_item_layout_3;
                    }
                    return 0;
                }
            }));
        }
    }
}
