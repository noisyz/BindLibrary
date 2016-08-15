package com.noisyz.noisybindexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.noisyz.databindinglibrary.bind.base.impl.adapter.BindAdapter;
import com.noisyz.databindinglibrary.callback.layoutcallback.LayoutResourceProvider;
import com.noisyz.noisybindexample.item.ExampleListItem;

import java.util.ArrayList;

public class ListViewMultitypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_activity_layout);
        ArrayList<ExampleListItem> itemList = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.listView);
        assert listView != null;
        BaseAdapter adapter = new BindAdapter<>(itemList,new LayoutResourceProvider<ExampleListItem>() {
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
        }).setItemViewCount(3);
        listView.setAdapter(adapter);
        for (int i = 0; i < 100000; i++) {
            itemList.add(new ExampleListItem("Title " + i, "Subtitle " + i, i % 2 == 0, i % 3));
        }
        adapter.notifyDataSetChanged();
    }
}
