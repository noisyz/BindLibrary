package com.noisyz.databindinglibrary.bind.base.impl.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noisyz.databindinglibrary.beans.BindObject;
import com.noisyz.databindinglibrary.beans.abs.MultiTypeObject;
import com.noisyz.databindinglibrary.bind.base.impl.ObjectDataBinder;
import com.noisyz.databindinglibrary.callback.clickevent.OnElementClickListener;
import com.noisyz.databindinglibrary.callback.layoutcallback.BaseLayoutResourceProvider;
import com.noisyz.databindinglibrary.callback.layoutcallback.LayoutResourceProvider;
import com.noisyz.databindinglibrary.callback.clickevent.OnElementClickListenerWrapper;
import com.noisyz.databindinglibrary.callback.clickevent.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 13.08.16.
 */
public class RecyclerBindAdapter<O extends BindObject> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int MODE_INVALID = -1;
    private static final int MODE_LIST = 0;
    private static final int MODE_ARRAY = 1;

    protected O[] os;
    protected List<O> itemList;
    private int mode;
    private LayoutResourceProvider mLayoutResourceProvider;
    private OnItemClickListener<O> onItemClickListener;
    private ArrayList<OnElementClickListenerWrapper> elementsClickWrappers;

    public RecyclerBindAdapter(List<O> itemList, int layoutResID) {
        mLayoutResourceProvider = new BaseLayoutResourceProvider<>(layoutResID);
        if (itemList == null) {
            itemList = new ArrayList<O>();
        }
        this.itemList = itemList;
        mode = MODE_LIST;
    }

    public RecyclerBindAdapter(O[] os, int layoutResID) {
        mLayoutResourceProvider = new BaseLayoutResourceProvider<>(layoutResID);
        if (os != null) {
            this.os = os;
            mode = MODE_ARRAY;
        } else mode = MODE_INVALID;
    }

    public RecyclerBindAdapter(List<O> itemList, LayoutResourceProvider mLayoutResourceProvider) {
        this(mLayoutResourceProvider);
        if (itemList == null) {
            itemList = new ArrayList<O>();
        }
        this.itemList = itemList;
        mode = MODE_LIST;
    }

    public RecyclerBindAdapter(O[] os, LayoutResourceProvider mLayoutResourceProvider) {
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

    public RecyclerBindAdapter setOnElementClickListener(int elementId, OnElementClickListener<O> onClickListener) {
        elementsClickWrappers.add(new OnElementClickListenerWrapper(elementId, onClickListener));
        return this;
    }

    public RecyclerBindAdapter setOnElementsClickListener(int[] elementIds, OnElementClickListener<O> onClickListener) {
        elementsClickWrappers.add(new OnElementClickListenerWrapper(elementIds, onClickListener));
        return this;
    }

    public RecyclerBindAdapter<O> setOnItemClickListener(OnItemClickListener<O> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int itemViewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new BindViewHolder(inflater.inflate(
                mLayoutResourceProvider.getLayoutResourceID(itemViewType), null));
    }

    @Override
    public int getItemViewType(int position) {
        O o = getItem(position);
        int viewType = 0;
        if (o instanceof MultiTypeObject) {
            viewType = ((MultiTypeObject) o).getType();
        }
        return viewType;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        O o = getItem(position);
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

    private class BindViewHolder extends RecyclerView.ViewHolder {
        private ObjectDataBinder mObjectDataBinder;

        public BindViewHolder(View itemView) {
            super(itemView);
        }

        public void bindView(O o) {
            if (mObjectDataBinder == null) {
                mObjectDataBinder = new ObjectDataBinder<>(o).registerView(itemView);
            } else {
                mObjectDataBinder.setObject(o);
            }
            mObjectDataBinder.bindUI();
        }
    }
}
