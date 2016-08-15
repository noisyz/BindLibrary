package com.noisyz.databindinglibrary.callback;

import com.noisyz.databindinglibrary.bind.base.UIBinder;

/**
 * Created by Oleg on 24.03.2016.
 */
public interface DataUpdatedCallback<O extends Object, V extends Object> {
    void onDataUpdated(UIBinder uiBinder, O object, String propertyName, V value);
}
