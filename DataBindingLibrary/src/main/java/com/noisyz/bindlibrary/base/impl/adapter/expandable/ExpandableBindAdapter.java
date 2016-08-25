package com.noisyz.databindinglibrary.bind.base.impl.adapter.expandable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.noisyz.databindinglibrary.beans.BindObject;
import com.noisyz.databindinglibrary.beans.abs.ParentObject;
import com.noisyz.databindinglibrary.beans.abs.SelectableObject;
import com.noisyz.databindinglibrary.bind.base.impl.ObjectDataBinder;
import com.noisyz.databindinglibrary.callback.clickevent.OnElementClickListener;
import com.noisyz.databindinglibrary.callback.clickevent.OnElementClickListenerWrapper;
import com.noisyz.databindinglibrary.callback.clickevent.OnItemClickListener;

import java.util.List;

/**
 * Created by oleg on 15.08.16.
 */
public class ExpandableBindAdapter<T extends BindObject & ParentObject, C extends BindObject> extends BaseExpandableListAdapter {
    protected List<T> itemList;
    private OnItemClickListener onParentItemClickListener, onChildItemClickListener;
    private List<OnElementClickListenerWrapper> parentElementsClickWrappers, childElementsClickWrappers;
    private int parentLayoutID, childLayoutID;

    public ExpandableBindAdapter(List<T> itemList, int parentLayoutID, int childLayoutID) {
        this.itemList = itemList;
        this.parentLayoutID = parentLayoutID;
        this.childLayoutID = childLayoutID;
    }

    public ExpandableBindAdapter<T, C> setOnElementClickListener(int elementId, OnElementClickListener onClickListener) {
        parentElementsClickWrappers.add(new OnElementClickListenerWrapper(elementId, onClickListener));
        return this;
    }

    public ExpandableBindAdapter<T, C> setOnElementsClickListener(int[] elementIds, OnElementClickListener onClickListener) {
        parentElementsClickWrappers.add(new OnElementClickListenerWrapper(elementIds, onClickListener));
        return this;
    }

    public ExpandableBindAdapter<T, C> setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onParentItemClickListener = onItemClickListener;
        return this;
    }

    public ExpandableBindAdapter<T, C> setOnChildElementClickListener(int elementId, OnElementClickListener onClickListener) {
        childElementsClickWrappers.add(new OnElementClickListenerWrapper(elementId, onClickListener));
        return this;
    }

    public ExpandableBindAdapter<T, C> setOnChildElementsClickListener(int[] elementIds, OnElementClickListener onClickListener) {
        childElementsClickWrappers.add(new OnElementClickListenerWrapper(elementIds, onClickListener));
        return this;
    }

    public ExpandableBindAdapter<T, C> setOnChildItemClickListener(OnItemClickListener onItemClickListener) {
        this.onChildItemClickListener = onItemClickListener;
        return this;
    }

    @Override
    public int getGroupCount() {
        return itemList.size();
    }

    @Override
    public int getChildrenCount(int position) {
        return itemList.get(position).getChilds().size();
    }

    @Override
    public Object getGroup(int position) {
        return itemList.get(position);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return itemList.get(groupPosition).getChilds().get(childPosition);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    static class BinderHolder {
        ObjectDataBinder objectDataBinder;
    }

    @Override
    public View getGroupView(final int position, boolean b, View view, ViewGroup viewGroup) {
        final T t = itemList.get(position);
        BinderHolder binderHolder = null;
        if (view == null) {
            binderHolder = new BinderHolder();
            view = LayoutInflater.from(viewGroup.getContext()).inflate(parentLayoutID, null);
            binderHolder.objectDataBinder = new ObjectDataBinder<T>(t).registerView(view);
            view.setTag(binderHolder);
        } else binderHolder = (BinderHolder) view.getTag();
        binderHolder.objectDataBinder.setObject(t);
        binderHolder.objectDataBinder.bindUI();

        final View finalView = view;

        if (onParentItemClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onParentItemClickListener.onItemClick(finalView, position, t);
                }
            });
        }

        if (parentElementsClickWrappers != null && !parentElementsClickWrappers.isEmpty()) {
            for (final OnElementClickListenerWrapper wrapper : parentElementsClickWrappers)
                for (final int elementId : wrapper.getIds()) {
                    View element = view.findViewById(elementId);
                    if (element != null) {
                        element.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                wrapper.getOnClickListener().onElementClick(finalView, elementId, position, t);
                            }
                        });
                    }
                }
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean b, View view, ViewGroup viewGroup) {
        final C c = (C) itemList.get(groupPosition).getChilds().get(childPosition);
        BinderHolder binderHolder = null;
        if (view == null) {
            binderHolder = new BinderHolder();
            view = LayoutInflater.from(viewGroup.getContext()).inflate(childLayoutID, null);
            binderHolder.objectDataBinder = new ObjectDataBinder<C>(c).registerView(view);
            view.setTag(binderHolder);
        } else binderHolder = (BinderHolder) view.getTag();
        binderHolder.objectDataBinder.setObject(c);
        binderHolder.objectDataBinder.bindUI();

        final View finalView = view;

        if (onChildItemClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChildItemClickListener.onItemClick(finalView, childPosition, c);
                }
            });
        }

        if (childElementsClickWrappers != null && !childElementsClickWrappers.isEmpty()) {
            for (final OnElementClickListenerWrapper wrapper : childElementsClickWrappers)
                for (final int elementId : wrapper.getIds()) {
                    View element = view.findViewById(elementId);
                    if (element != null) {
                        element.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                wrapper.getOnClickListener().onElementClick(finalView, elementId, childPosition, c);
                            }
                        });
                    }
                }
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        BindObject object = (BindObject) getChild(groupPosition, childPosition);
        return object instanceof SelectableObject;
    }
}
