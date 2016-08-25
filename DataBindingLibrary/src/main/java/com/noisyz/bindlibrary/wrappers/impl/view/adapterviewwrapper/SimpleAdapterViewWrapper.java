package com.noisyz.databindinglibrary.wrappers.impl.view.adapterviewwrapper;

import android.view.View;
import android.widget.AdapterView;

import com.noisyz.databindinglibrary.wrappers.impl.view.AbsViewWrapper;

/**
 * Created by Oleg on 18.03.2016.
 */
public class SimpleAdapterViewWrapper extends AbsViewWrapper<AdapterView>
        implements AdapterView.OnItemSelectedListener{

    private int indent;

    public SimpleAdapterViewWrapper(AdapterView adapterView, int indent) {
        super(adapterView);
        this.indent = indent;
        adapterView.setOnItemSelectedListener(this);
    }


    @Override
    public void bindUI(Object value) {
        if (value != null) {
            getView().setSelection(Integer.parseInt(value.toString()) - indent);
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        bindObject(position + indent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
