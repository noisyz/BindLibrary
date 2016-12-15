package com.noisyz.bindlibrary.base.impl.adapter.expandable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.noisyz.bindlibrary.base.impl.ObjectViewBinder;
import com.noisyz.bindlibrary.callback.ChildrenProvider;
import com.noisyz.bindlibrary.callback.clickevent.OnElementClickListener;
import com.noisyz.bindlibrary.callback.clickevent.OnElementClickListenerWrapper;
import com.noisyz.bindlibrary.callback.clickevent.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 15.08.16.
 */
public class ExpandableBindAdapter extends BaseExpandableListAdapter {
    protected List itemList;
    private OnItemClickListener onParentItemClickListener, onChildItemClickListener;
    private List<OnElementClickListenerWrapper> parentElementsClickWrappers, childElementsClickWrappers;
    private ChildrenProvider childrenProvider;
    private int parentLayoutID, childLayoutID;

    public ExpandableBindAdapter(List itemList, int parentLayoutID, int childLayoutID, ChildrenProvider childrenProvider) {
        this.itemList = itemList;
        this.parentLayoutID = parentLayoutID;
        this.childLayoutID = childLayoutID;
        this.childrenProvider = childrenProvider;
        parentElementsClickWrappers = new ArrayList<>();
        childElementsClickWrappers = new ArrayList<>();
    }

    public ExpandableBindAdapter setOnElementClickListener(int elementId, OnElementClickListener onClickListener) {
        parentElementsClickWrappers.add(new OnElementClickListenerWrapper(elementId, onClickListener));
        return this;
    }

    public ExpandableBindAdapter setOnElementsClickListener(OnElementClickListener onClickListener, int... elementIds) {
        parentElementsClickWrappers.add(new OnElementClickListenerWrapper(elementIds, onClickListener));
        return this;
    }

    public ExpandableBindAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onParentItemClickListener = onItemClickListener;
        return this;
    }

    public ExpandableBindAdapter setOnChildElementClickListener(int elementId, OnElementClickListener onClickListener) {
        childElementsClickWrappers.add(new OnElementClickListenerWrapper(elementId, onClickListener));
        return this;
    }

    public ExpandableBindAdapter setOnChildElementsClickListener(OnElementClickListener onClickListener, int... elementIds) {
        childElementsClickWrappers.add(new OnElementClickListenerWrapper(elementIds, onClickListener));
        return this;
    }

    public ExpandableBindAdapter setOnChildItemClickListener(OnItemClickListener onItemClickListener) {
        this.onChildItemClickListener = onItemClickListener;
        return this;
    }

    @Override
    public int getGroupCount() {
        return itemList.size();
    }

    @Override
    public int getChildrenCount(int position) {
        return childrenProvider.getChildren(itemList.get(position)).size();
    }

    @Override
    public Object getGroup(int position) {
        return itemList.get(position);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childrenProvider.getChildren(itemList.get(childPosition)).size();
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
        ObjectViewBinder objectViewBinder;
    }

    @Override
    public View getGroupView(final int position, boolean b, View view, ViewGroup viewGroup) {
        final Object object = itemList.get(position);
        BinderHolder binderHolder = null;
        if (view == null) {
            binderHolder = new BinderHolder();
            view = LayoutInflater.from(viewGroup.getContext()).inflate(parentLayoutID, null);
            binderHolder.objectViewBinder = new ObjectViewBinder(object).registerView(view);
            view.setTag(binderHolder);
        } else {
            binderHolder = (BinderHolder) view.getTag();
            binderHolder.objectViewBinder.setObject(object);
        }
        binderHolder.objectViewBinder.bindUI();

        final View finalView = view;

        if (onParentItemClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onParentItemClickListener.onItemClick(finalView, position, object);
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
                                wrapper.getOnClickListener().onElementClick(finalView, elementId, position, object);
                            }
                        });
                    }
                }
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean b, View view, ViewGroup viewGroup) {
        final Object object = getChild(groupPosition, childPosition);
        BinderHolder binderHolder = null;
        if (view == null) {
            binderHolder = new BinderHolder();
            view = LayoutInflater.from(viewGroup.getContext()).inflate(childLayoutID, null);
            binderHolder.objectViewBinder = new ObjectViewBinder(object).registerView(view);
            view.setTag(binderHolder);
        } else {
            binderHolder = (BinderHolder) view.getTag();
            binderHolder.objectViewBinder.setObject(object);
        }
        binderHolder.objectViewBinder.bindUI();

        final View finalView = view;

        if (onChildItemClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChildItemClickListener.onItemClick(finalView, childPosition, object);
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
                                wrapper.getOnClickListener().onElementClick(finalView, elementId, childPosition, object);
                            }
                        });
                    }
                }
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
