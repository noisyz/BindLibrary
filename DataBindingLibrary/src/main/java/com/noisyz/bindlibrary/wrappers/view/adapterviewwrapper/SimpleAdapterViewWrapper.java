package com.noisyz.bindlibrary.wrappers.view.adapterviewwrapper;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.noisyz.bindlibrary.wrappers.ViewBinder;

/**
 * Created by Oleg on 18.03.2016.
 */
public class SimpleAdapterViewWrapper extends ViewBinder<Integer, AdapterView>
        implements AdapterView.OnItemSelectedListener {

    private int itemLayoutID, itemsArrayID;

    public SimpleAdapterViewWrapper(int itemLayoutID, int itemsArrayID) {
        this.itemLayoutID = itemLayoutID;
        this.itemsArrayID = itemsArrayID;
    }

    @Override
    public void addListeners(AdapterView adapterView) {
        adapterView.setOnItemSelectedListener(this);
    }

    @Override
    public void removeListeners(AdapterView adapterView) {
        adapterView.setOnItemSelectedListener(null);
    }

    @Override
    public Integer getViewValue(AdapterView adapterView) {

        return adapterView.getSelectedItemPosition();
    }


    @Override
    public void bindUI(Integer integer, AdapterView adapterView) {
        String[] values = adapterView.getContext().getResources().getStringArray(itemsArrayID);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(adapterView.getContext(), itemLayoutID, values);
        adapterView.setAdapter(spinnerArrayAdapter);
        adapterView.setSelection(integer);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        bindObject(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
