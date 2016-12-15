package com.noisyz.bindlibrary.base.impl.adapter.recycler;

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
public class RecyclerBindAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int MODE_INVALID = -1;
    private static final int MODE_LIST = 0;
    private static final int MODE_ARRAY = 1;

    protected Object[] os;
    protected List<Object> itemList;
    private int mode;
    private LayoutResourceProvider mLayoutResourceProvider;
    private OnItemClickListener onItemClickListener;
    private ArrayList<OnElementClickListenerWrapper> elementsClickWrappers;

    public RecyclerBindAdapter(List itemList, int layoutResID) {
        mLayoutResourceProvider = new EmptyBaseLayoutResourceProvider(layoutResID);
        if (itemList == null) {
            itemList = new ArrayList<>();
        }
        this.itemList = itemList;
        mode = MODE_LIST;
        elementsClickWrappers = new ArrayList<>();
    }

    public RecyclerBindAdapter(Object[] os, int layoutResID) {
        mLayoutResourceProvider = new EmptyBaseLayoutResourceProvider(layoutResID);
        if (os != null) {
            this.os = os;
            mode = MODE_ARRAY;
        } else mode = MODE_INVALID;
        elementsClickWrappers = new ArrayList<>();
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

    public RecyclerBindAdapter setOnElementsClickListener(OnElementClickListener onClickListener, int... elementIds) {
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Object o = getItem(position);
        BindViewHolder bindViewHolder = (BindViewHolder) holder;
        bindViewHolder.bindView(o);

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
            for (final OnElementClickListenerWrapper wrapper : elementsClickWrappers)
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
        private ObjectViewBinder mObjectViewBinder;

        public BindViewHolder(View itemView) {
            super(itemView);
        }

        public void bindView(Object o) {
            if (mObjectViewBinder == null) {
                mObjectViewBinder = new ObjectViewBinder(o).registerView(itemView);
            } else {
                mObjectViewBinder.setObject(o);
            }
            mObjectViewBinder.bindUI();
        }
    }
}
