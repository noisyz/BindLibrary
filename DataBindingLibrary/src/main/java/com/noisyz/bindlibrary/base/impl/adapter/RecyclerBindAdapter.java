package com.noisyz.bindlibrary.base.impl.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noisyz.bindlibrary.base.impl.ObjectViewBinder;
import com.noisyz.bindlibrary.callback.clickevent.OnElementClickListener;
import com.noisyz.bindlibrary.callback.clickevent.OnElementClickListenerWrapper;
import com.noisyz.bindlibrary.callback.clickevent.OnItemClickListener;
import com.noisyz.bindlibrary.callback.layoutcallback.EmptyBaseLayoutResourceProvider;
import com.noisyz.bindlibrary.callback.layoutcallback.LayoutResourceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 06.09.16.
 */
public abstract class RecyclerBindAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<T> itemList;
    private LayoutResourceProvider<T> mLayoutResourceProvider;
    private OnItemClickListener<T> onItemClickListener;
    private List<OnElementClickListenerWrapper<T>> elementsClickWrappers;

    public RecyclerBindAdapter(List<T> itemList, int layoutResID) {
        mLayoutResourceProvider = new EmptyBaseLayoutResourceProvider<>(layoutResID);
        if (itemList == null) {
            itemList = new ArrayList<>();
        }
        this.itemList = itemList;
        elementsClickWrappers = new ArrayList<>();
    }

    public RecyclerBindAdapter(List<T> itemList, LayoutResourceProvider<T> mLayoutResourceProvider) {
        this(mLayoutResourceProvider);
        if (itemList == null) {
            itemList = new ArrayList<>();
        }
        this.itemList = itemList;
    }

    private RecyclerBindAdapter(final LayoutResourceProvider<T> mLayoutResourceProvider) {
        this.mLayoutResourceProvider = mLayoutResourceProvider;
        elementsClickWrappers = new ArrayList<>();
    }

    public RecyclerBindAdapter setOnElementClickListener(int elementId, OnElementClickListener<T> onClickListener) {
        elementsClickWrappers.add(new OnElementClickListenerWrapper<>(elementId, onClickListener));
        return this;
    }

    public RecyclerBindAdapter setOnElementsClickListener(OnElementClickListener<T> onClickListener, int... elementIds) {
        elementsClickWrappers.add(new OnElementClickListenerWrapper<>(elementIds, onClickListener));
        return this;
    }

    public RecyclerBindAdapter setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    @Override
    public BindViewHolder onCreateViewHolder(ViewGroup parent, int itemViewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new BindViewHolder(inflater.inflate(
                mLayoutResourceProvider.getLayoutResourceID(itemViewType), parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return mLayoutResourceProvider.getItemViewType(getItem(position));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        T t = getItem(position);
        BindViewHolder bindViewHolder = (BindViewHolder) holder;
        bindViewHolder.bindView(t);

        final View finalView = bindViewHolder.itemView;
        if (onItemClickListener != null) {
            finalView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(finalView, position, getItem(position));
                }
            });
        }

        if (elementsClickWrappers != null && !elementsClickWrappers.isEmpty()) {
            for (final OnElementClickListenerWrapper<T> wrapper : elementsClickWrappers)
                for (final int elementId : wrapper.getIds()) {
                    View element = finalView.findViewById(elementId);
                    if (element != null) {
                        element.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                wrapper.getOnClickListener().onElementClick(finalView, elementId, position, getItem(position));
                            }
                        });
                    }
                }
        }
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    public T getItem(int position) {
        return itemList != null ? itemList.get(position) : null;
    }

    public List<T> getItems() {
        return itemList;
    }

    public void setItems(List<T> items) {
        this.itemList = items;
        notifyDataSetChanged();
    }

    protected abstract ObjectViewBinder<T> initObjectViewBinder(T t);

    private class BindViewHolder extends RecyclerView.ViewHolder {

        private ObjectViewBinder<T> objectViewBinder;

        public BindViewHolder(View itemView) {
            super(itemView);
        }

        public void bindView(T t) {
            if (objectViewBinder == null) {
                objectViewBinder = initObjectViewBinder(t).registerView(itemView);
            } else {
                objectViewBinder.registerView(itemView).setBindObject(t);
            }
            objectViewBinder.bindUI();
        }
    }
}
