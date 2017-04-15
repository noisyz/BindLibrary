package com.noisyz.bindlibrary.wrappers.view.simple;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.TextView;

import com.noisyz.bindlibrary.wrappers.ViewBinder;

/**
 * Created by Oleg on 17.03.2016.
 */
public class TextViewWrapper extends ViewBinder<String, TextView> implements TextWatcher {

    @Override
    public void addListeners(TextView textView) {
        textView.addTextChangedListener(this);
    }

    @Override
    public void removeListeners(TextView textView) {
        textView.removeTextChangedListener(this);
    }

    @Override
    public String getViewValue(TextView textView) {
        return textView.getText().toString().trim();
    }

    @Override
    public void bindUI(String value, TextView textView) {
        if (!TextUtils.isEmpty(value) && !value.equals("null"))
            textView.setText(value);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        bindObject(editable.toString());
    }
}
