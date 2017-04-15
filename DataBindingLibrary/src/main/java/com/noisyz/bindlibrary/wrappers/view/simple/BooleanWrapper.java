package com.noisyz.bindlibrary.wrappers.view.simple;

import android.view.View;
import android.widget.CompoundButton;

import com.noisyz.bindlibrary.wrappers.ViewBinder;

/**
 * Created by Oleg on 17.03.2016.
 */
public class BooleanWrapper extends ViewBinder<Boolean, View> implements CompoundButton.OnCheckedChangeListener {

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        bindObject(isChecked);
    }

    @Override
    public void addListeners(View view) {
        if (view instanceof CompoundButton) {
            CompoundButton compoundButton = (CompoundButton) view;
            compoundButton.setOnCheckedChangeListener(this);
        }
    }

    @Override
    public void removeListeners(View view) {
        if (view instanceof CompoundButton) {
            CompoundButton compoundButton = (CompoundButton) view;
            compoundButton.setOnCheckedChangeListener(null);
        }
    }

    @Override
    public Boolean getViewValue(View view) {
        if (view instanceof CompoundButton) {
            CompoundButton compoundButton = (CompoundButton) view;
            return compoundButton.isChecked();
        } else return view.getVisibility() == View.VISIBLE;
    }

    @Override
    public void bindUI(Boolean aBoolean, View view) {
        if (view instanceof CompoundButton) {
            CompoundButton compoundButton = (CompoundButton) view;
            compoundButton.setChecked(aBoolean);
        } else
            view.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
    }
}
