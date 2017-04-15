package com.noisyz.bindlibrary.property;

import com.noisyz.bindlibrary.models.key.Key;
import com.noisyz.bindlibrary.property.abs.Property;
import com.noisyz.bindlibrary.wrappers.PropertyViewWrapper;
import com.noisyz.bindlibrary.wrappers.ValueProvider;
import com.noisyz.bindlibrary.wrappers.view.adapterviewwrapper.SimpleAdapterViewWrapper;

/**
 * Created by nero232 on 15.04.17.
 */

public class SelectableIntegerProperty<BO> extends Property<BO, Integer> {

    private int itemLayoutID, itemsArrayID;

    public SelectableIntegerProperty(int itemLayoutID, int itemsArrayID, ValueProvider<BO, Integer> valueProvider, Key key) {
        super(valueProvider, key);
        this.itemLayoutID = itemLayoutID;
        this.itemsArrayID = itemsArrayID;
    }

    @Override
    public PropertyViewWrapper buildPropertyViewWrapper(BO bo) {
        return new PropertyViewWrapper<>(new SimpleAdapterViewWrapper(itemLayoutID, itemsArrayID),
                getValueProvider(), bo, getKey()
        );
    }
}
