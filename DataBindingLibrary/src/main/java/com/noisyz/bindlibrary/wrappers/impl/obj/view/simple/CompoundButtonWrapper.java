package com.noisyz.bindlibrary.wrappers.impl.obj.view.simple;

import android.widget.CompoundButton;

import com.noisyz.bindlibrary.wrappers.impl.obj.ViewBinder;

/**
 * Created by Oleg on 17.03.2016.
 */
public class CompoundButtonWrapper extends ViewBinder<Boolean, CompoundButton> implements CompoundButton.OnCheckedChangeListener {

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        bindObject();
    }

    @Override
    public void addListeners(CompoundButton compoundButton) {
        compoundButton.setOnCheckedChangeListener(this);
    }

    @Override
    public void removeListeners(CompoundButton compoundButton) {
        compoundButton.setOnCheckedChangeListener(null);
    }

    @Override
    public Boolean getViewValue(CompoundButton compoundButton) {
        return compoundButton.isChecked();
    }

    @Override
    public void bindUI(Boolean aBoolean, CompoundButton compoundButton) {
        compoundButton.setChecked(aBoolean);
    }
}
