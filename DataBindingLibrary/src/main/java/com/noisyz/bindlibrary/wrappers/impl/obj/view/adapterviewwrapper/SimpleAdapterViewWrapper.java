package com.noisyz.bindlibrary.wrappers.impl.obj.view.adapterviewwrapper;

import android.view.View;
import android.widget.AdapterView;

import com.noisyz.bindlibrary.wrappers.impl.obj.ViewBinder;

/**
 * Created by Oleg on 18.03.2016.
 */
public class SimpleAdapterViewWrapper extends ViewBinder<Integer, AdapterView>
        implements AdapterView.OnItemSelectedListener {

    private int indent;

    public SimpleAdapterViewWrapper(int indent) {
        this.indent = indent;
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
        return adapterView.getSelectedItemPosition() + indent;
    }


    @Override
    public void bindUI(Integer integer, AdapterView adapterView) {
        adapterView.setSelection(integer - indent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        bindObject();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
