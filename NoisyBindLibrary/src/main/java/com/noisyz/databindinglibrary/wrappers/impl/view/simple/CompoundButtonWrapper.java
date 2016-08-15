package com.noisyz.databindinglibrary.wrappers.impl.view.simple;

import android.widget.CompoundButton;

import com.noisyz.databindinglibrary.wrappers.ObjectBinder;
import com.noisyz.databindinglibrary.wrappers.impl.view.AbsViewWrapper;

/**
 * Created by Oleg on 17.03.2016.
 */
public class CompoundButtonWrapper extends AbsViewWrapper<CompoundButton> implements CompoundButton.OnCheckedChangeListener {

    public CompoundButtonWrapper(CompoundButton compoundButton) {
        super(compoundButton);
        compoundButton.setOnCheckedChangeListener(this);
    }

    @Override
    public void bindUI(Object object) {
        getView().setOnCheckedChangeListener(null);
        if (object != null) {
            boolean value = Boolean.valueOf(object.toString());
            getView().setChecked(value);
        }
        getView().setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        bindObject(isChecked);
    }
}
