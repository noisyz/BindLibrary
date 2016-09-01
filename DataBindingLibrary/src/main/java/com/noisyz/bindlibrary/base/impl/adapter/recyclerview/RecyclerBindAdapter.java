package com.noisyz.bindlibrary.base.impl.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.noisyz.bindlibrary.base.impl.ObjectDataBinder;
import com.noisyz.bindlibrary.callback.clickevent.OnElementClickListener;
import com.noisyz.bindlibrary.callback.layoutcallback.EmptyLayoutResourceProvider;
import com.noisyz.bindlibrary.callback.layoutcallback.LayoutResourceProvider;
import com.noisyz.bindlibrary.callback.clickevent.OnElementClickListenerWrapper;
import com.noisyz.bindlibrary.callback.clickevent.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 13.08.16.
 */
public class RecyclerBindAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int MODE_INVALID = -1;
    private static final int MODE_LIST = 0;
    private static final int MODE_ARRAY = 1;

    protected Object[] os;
    protected List itemList;
    private int mode;
    private LayoutResourceProvider mLayoutResourceProvider;
    private OnItemClickListener onItemClickListener;
    private ArrayList<OnElementClickListenerWrapper> elementsClickWrappers;

    public RecyclerBindAdapter(List itemList, int layoutResID) {
        mLayoutResourceProvider = new EmptyLayoutResourceProvider(layoutResID);
        if (itemList == null) {
            itemList = new ArrayList<>();
        }
        this.itemList = itemList;
        mode = MODE_LIST;
    }

    public RecyclerBindAdapter(Object[] os, int layoutResID) {
        mLayoutResourceProvider = new EmptyLayoutResourceProvider(layoutResID);
        if (os != null) {
            this.os = os;
            mode = MODE_ARRAY;
        } else mode = MODE_INVALID;
    }

    public RecyclerBindAdapter(List itemList, LayoutResourceProvider mLayoutResourceProvider) {
        this(mLayoutResourceProvider);
        if (itemList == null) {
            itemList = new ArrayList<>();
        }
        this.itemList = itemList;
        mode = MODE_LIST;
    }

    public RecyclerBindAdapter(Object[] os, LayoutResourceProvider mLayoutResourceProvider) {
        this(mLayoutResourceProvider);
        if (os != null) {
            this.os = os;
            mode = MODE_ARRAY;
        } else mode = MODE_INVALID;
    }

    private RecyclerBindAdapter(final LayoutResourceProvider mLayoutResourceProvider) {
        this.mLayoutResourceProvider = mLayoutResourceProvider;
        elementsClickWrappers = new ArrayList<>();
    }

    public RecyclerBindAdapter setOnElementClickListener(int elementId, OnElementClickListener onClickListener) {
        elementsClickWrappers.add(new OnElementClickListenerWrapper(elementId, onClickListener));
        return this;
    }

    public RecyclerBindAdapter setOnElementsClickListener(int[] elementIds, OnElementClickListener onClickListener) {
        elementsClickWrappers.add(new OnElementClickListenerWrapper(elementIds, onClickListener));
        return this;
    }

    public RecyclerBindAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int itemViewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new BindViewHolder(inflater.inflate(
                mLayoutResourceProvider.getLayoutResourceID(itemViewType), parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return mLayoutResourceProvider.getItemViewType(getItem(position));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int index) {
        final int position = index;
        Object object = getItem(position);
        BindViewHolder bindViewHolder = (BindViewHolder) holder;
        bindViewHolder.bindView(object);

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView, position, getItem(position));
                }
            });
        }

        if (elementsClickWrappers != null && !elementsClickWrappers.isEmpty()) {
            for (final OnElementClickListenerWrapper wrapper : elementsClickWrappers)
                for (final int elementId : wrapper.getIds()) {
                    View element = holder.itemView.findViewById(elementId);
                    if (element != null) {
                        element.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                wrapper.getOnClickListener().onElementClick(holder.itemView, elementId, position, getItem(position));
                            }
                        });
                    }
                }
        }
    }

    @Override
    public int getItemCount() {
        switch (mode) {
            case MODE_ARRAY:
                return os.length;
            case MODE_LIST:
                return itemList.size();
            default:
                return 0;
        }
    }

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

    private class BindViewHolder extends RecyclerView.ViewHolder {
        private ObjectDataBinder mObjectDataBinder;

        public BindViewHolder(View itemView) {
            super(itemView);
        }

        public void bindView(Object object) {
            if (mObjectDataBinder == null) {
                mObjectDataBinder = new ObjectDataBinder(object).registerView(itemView);
            } else {
                mObjectDataBinder.setObject(object);
            }
            mObjectDataBinder.bindUI();
        }
    }
}
