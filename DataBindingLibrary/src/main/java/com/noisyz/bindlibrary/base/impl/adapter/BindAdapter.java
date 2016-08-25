package com.noisyz.databindinglibrary.bind.base.impl.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.noisyz.databindinglibrary.beans.BindObject;
import com.noisyz.databindinglibrary.beans.abs.MultiTypeObject;
import com.noisyz.databindinglibrary.bind.base.impl.ObjectDataBinder;
import com.noisyz.databindinglibrary.callback.clickevent.OnElementClickListener;
import com.noisyz.databindinglibrary.callback.clickevent.OnElementClickListenerWrapper;
import com.noisyz.databindinglibrary.callback.clickevent.OnItemClickListener;
import com.noisyz.databindinglibrary.callback.layoutcallback.BaseLayoutResourceProvider;
import com.noisyz.databindinglibrary.callback.layoutcallback.LayoutResourceProvider;
import com.noisyz.databindinglibrary.exception.EmptyBindAdapterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleg on 23.03.2016.
 */
public class BindAdapter<O extends BindObject> extends BaseAdapter {

    private static final int MODE_INVALID = -1;
    private static final int MODE_LIST = 0;
    private static final int MODE_ARRAY = 1;

    protected O[] os;
    protected List<O> itemList;
    private int mode, viewTypeCount;
    private LayoutResourceProvider mLayoutResourceProvider;
    private OnItemClickListener<O> onItemClickListener;
    private List<OnElementClickListenerWrapper> elementsClickWrappers;

    public BindAdapter(List<O> itemList, int layoutResID) {
        mLayoutResourceProvider = new BaseLayoutResourceProvider(layoutResID);
        if (itemList == null) {
            itemList = new ArrayList<O>();
        }
        this.itemList = itemList;
        mode = MODE_LIST;
        viewTypeCount = 1;
    }

    public BindAdapter(O[] os, int layoutResID) {
        mLayoutResourceProvider = new BaseLayoutResourceProvider(layoutResID);
        if (os != null) {
            this.os = os;
            mode = MODE_ARRAY;
        } else mode = MODE_INVALID;
        viewTypeCount = 1;
    }

    public BindAdapter(List<O> itemList, LayoutResourceProvider mLayoutResourceProvider) {
        this(mLayoutResourceProvider);
        if (itemList == null) {
            itemList = new ArrayList<O>();
        }
        this.itemList = itemList;
        mode = MODE_LIST;
    }

    public BindAdapter(O[] os, LayoutResourceProvider mLayoutResourceProvider) {
        this(mLayoutResourceProvider);
        if (os != null) {
            this.os = os;
            mode = MODE_ARRAY;
        } else mode = MODE_INVALID;
    }

    private BindAdapter(final LayoutResourceProvider mLayoutResourceProvider) {
        this.mLayoutResourceProvider = mLayoutResourceProvider;
        elementsClickWrappers = new ArrayList<>();
    }

    public BindAdapter setItemViewCount(int itemViewCount){
        this.viewTypeCount = itemViewCount;
        return this;
    }

    public BindAdapter setOnElementClickListener(int elementId, OnElementClickListener<O> onClickListener) {
        elementsClickWrappers.add(new OnElementClickListenerWrapper(elementId, onClickListener));
        return this;
    }

    public BindAdapter setOnElementsClickListener(int[] elementIds, OnElementClickListener onClickListener) {
        elementsClickWrappers.add(new OnElementClickListenerWrapper(elementIds, onClickListener));
        return this;
    }

    public BindAdapter<O> setOnItemClickListener(OnItemClickListener<O> onItemClickListener) {
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
    public O getItem(int position) {
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
    public int getItemViewType(int position){
        O o = getItem(position);
        int viewType = 0;
        if(o instanceof MultiTypeObject){
            viewType = ((MultiTypeObject) o).getType();
        }
        return viewType;
    }

    @Override
    public int getViewTypeCount(){
        if(!isEmpty()) {
            O o = getItem(0);
            if (o instanceof MultiTypeObject) {
                viewTypeCount = ((MultiTypeObject) o).getTypeCount();
            }
        } else if(viewTypeCount==0)
            throw new EmptyBindAdapterException();
        return viewTypeCount;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    static class BinderHolder {
        ObjectDataBinder objectDataBinder;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        BinderHolder binderHolder = null;
        final O o = getItem(position);
        int currentItemLayoutID = o instanceof MultiTypeObject?
                mLayoutResourceProvider.getLayoutResourceID(((MultiTypeObject) o).getType()):
                mLayoutResourceProvider.getLayoutResourceID(0);

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(currentItemLayoutID, null);
            binderHolder = new BinderHolder();
            binderHolder.objectDataBinder = new ObjectDataBinder<>(o).registerView(view);
            view.setTag(binderHolder);
        }
        else binderHolder = (BinderHolder) view.getTag();

        ObjectDataBinder objectDataBinder = binderHolder.objectDataBinder;
        objectDataBinder.setObject(o);
        objectDataBinder.bindUI();

        final View finalView = view;

        if (onItemClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(finalView, position, o);
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
                                wrapper.getOnClickListener().onElementClick(finalView, elementId, position, o);
                            }
                        });
                    }
                }
        }
        return view;
    }

}
