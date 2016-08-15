package com.noisyz.noisybindexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.noisyz.noisybindexample.item.ExampleListItem;

import java.util.ArrayList;

/**
 * Created by oleg on 14.08.16.
 */
public class MultiTypeItemAdapter extends BaseAdapter {
    private ArrayList<ExampleListItem> itemList;

    public MultiTypeItemAdapter(ArrayList<ExampleListItem> itemList){
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolderTypeOne{

    }

    static class ViewHolderTypeTwo{

    }

    static class ViewHolderTypeThree{

    }

    @Override
    public int getItemViewType(int position){
        return itemList.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        switch (getItemViewType(i)){
            case ExampleListItem.RANDOM_TYPE_1:
                if(view==null){
                    view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.example_list_item_layout, null);
                    }
                break;
            case ExampleListItem.RANDOM_TYPE_2:
                if(view==null){
                    view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.example_list_item_layout_2, null);
                }
                break;
            case ExampleListItem.RANDOM_TYPE_3:
                if(view==null){
                    view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.example_list_item_layout_3, null);
                }
                break;
        }
        return view;
    }
}
