package com.noisyz.databindinglibrary.bind.base.impl.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.noisyz.databindinglibrary.beans.BindObject;
import com.noisyz.databindinglibrary.bind.base.impl.ObjectDataBinder;
import com.noisyz.databindinglibrary.callback.clickevent.OnItemClickListener;

import java.util.List;

/**
 * Created by Oleg on 23.03.2016.
 */
public abstract class ConvertBindAdapter<O extends BindObject> extends BindAdapter<O> {

    private ObjectDataBinder<ConvertBindAdapter> adapterDataBinder;

    public ConvertBindAdapter(List<O> itemList, int layoutResID) {
        super(itemList, layoutResID);
        initAdapterBinder();
    }

    public ConvertBindAdapter(Context context, O[] os, int layoutResID) {
        super(os, layoutResID);
        initAdapterBinder();
    }

    private void initAdapterBinder() {
        adapterDataBinder = new ObjectDataBinder<ConvertBindAdapter>(this);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);
        adapterDataBinder.registerView(convertView);
        updateFieldsByObject(convertView, position, getItem(position));
        adapterDataBinder.bindUI();
        return convertView;
    }


    @Override
    public ConvertBindAdapter<O> setOnItemClickListener(OnItemClickListener listener) {
        return (ConvertBindAdapter<O>) super.setOnItemClickListener(listener);
    }

    protected abstract void updateFieldsByObject(View convertView, int position, O o);
}
