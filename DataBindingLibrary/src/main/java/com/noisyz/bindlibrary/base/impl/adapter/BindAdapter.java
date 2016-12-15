package com.noisyz.bindlibrary.base.impl.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.noisyz.bindlibrary.base.impl.ObjectViewBinder;
import com.noisyz.bindlibrary.callback.clickevent.OnElementClickListener;
import com.noisyz.bindlibrary.callback.clickevent.OnElementClickListenerWrapper;
import com.noisyz.bindlibrary.callback.clickevent.OnItemClickListener;
import com.noisyz.bindlibrary.callback.layoutcallback.BaseLayoutResourceProvider;
import com.noisyz.bindlibrary.callback.layoutcallback.EmptyBaseLayoutResourceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleg on 23.03.2016.
 */
public class BindAdapter extends BaseAdapter {

    private static final int MODE_INVALID = -1;
    private static final int MODE_LIST = 0;
    private static final int MODE_ARRAY = 1;

    protected Object[] os;
    protected List itemList;
    private int mode;
    private BaseLayoutResourceProvider mLayoutResourceProvider;
    private OnItemClickListener onItemClickListener;
    private List<OnElementClickListenerWrapper> elementsClickWrappers;

    public BindAdapter(List itemList, int layoutResID) {
        mLayoutResourceProvider = new EmptyBaseLayoutResourceProvider(layoutResID);
        if (itemList == null) {
            itemList = new ArrayList();
        }
        this.itemList = itemList;
        mode = MODE_LIST;
        elementsClickWrappers = new ArrayList<>();
    }

    public BindAdapter(Object[] os, int layoutResID) {
        mLayoutResourceProvider = new EmptyBaseLayoutResourceProvider(layoutResID);
        if (os != null) {
            this.os = os;
            mode = MODE_ARRAY;
        } else mode = MODE_INVALID;
        elementsClickWrappers = new ArrayList<>();
    }

    public BindAdapter(List itemList, BaseLayoutResourceProvider mLayoutResourceProvider) {
        this(mLayoutResourceProvider);
        if (itemList == null) {
            itemList = new ArrayList();
        }
        this.itemList = itemList;
        mode = MODE_LIST;
    }

    public BindAdapter(Object[] os, BaseLayoutResourceProvider mLayoutResourceProvider) {
        this(mLayoutResourceProvider);
        if (os != null) {
            this.os = os;
            mode = MODE_ARRAY;
        } else mode = MODE_INVALID;
    }

    private BindAdapter(final BaseLayoutResourceProvider mLayoutResourceProvider) {
        this.mLayoutResourceProvider = mLayoutResourceProvider;
        elementsClickWrappers = new ArrayList<>();
    }

    public BindAdapter setOnElementClickListener(int elementId, OnElementClickListener onClickListener) {
        elementsClickWrappers.add(new OnElementClickListenerWrapper(elementId, onClickListener));
        return this;
    }

    public BindAdapter setOnElementsClickListener(OnElementClickListener onClickListener, int... elementIds) {
        elementsClickWrappers.add(new OnElementClickListenerWrapper(elementIds, onClickListener));
        return this;
    }

    public BindAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    @Override
    public int getCount() {
        switch (mode) {
            case MODE_ARRAY:
                return os.length;
            case MODE_LIST:
                return itemList.size();
            default:
                return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        switch (mode) {
            case MODE_ARRAY:
                return os[position];
            case MODE_LIST:
                return itemList.get(position);
            default:
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mLayoutResourceProvider.getItemViewType(getItem(position));
    }

    @Override
    public int getViewTypeCount() {
        return mLayoutResourceProvider.getLayoutCount();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    static class BinderHolder {
        ObjectViewBinder objectViewBinder;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        BinderHolder binderHolder = null;
        final Object object = getItem(position);
        int currentItemLayoutID = mLayoutResourceProvider.getLayoutResourceID(getItemViewType(position));

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(currentItemLayoutID, null);
            binderHolder = new BinderHolder();
            binderHolder.objectViewBinder = new ObjectViewBinder(object).registerView(view);
            view.setTag(binderHolder);
        } else {
            binderHolder = (BinderHolder) view.getTag();
            binderHolder.objectViewBinder.setObject(object);
        }
        binderHolder.objectViewBinder.bindUI();

        final View finalView = view;

        if (onItemClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(finalView, position, object);
                }
            });
        }

        if (elementsClickWrappers != null && !elementsClickWrappers.isEmpty()) {
            for (final OnElementClickListenerWrapper wrapper : elementsClickWrappers)
                for (final int elementId : wrapper.getIds()) {
                    View element = view.findViewById(elementId);
                    if (element != null) {
                        element.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                wrapper.getOnClickListener().onElementClick(finalView, elementId, position, object);
                            }
                        });
                    }
                }
        }
        return view;
    }

}
