package com.noisyz.bindlibrary.wrappers.view.simple;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.TextView;

import com.noisyz.bindlibrary.wrappers.ViewBinder;

/**
 * Created by Oleg on 18.03.2016.
 */
public class FloatTextWrapper extends ViewBinder<Float, TextView> implements TextWatcher{

    @Override
    public void addListeners(TextView textView) {
        textView.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        textView.addTextChangedListener(this);
    }

    @Override
    public void removeListeners(TextView textView) {
        textView.removeTextChangedListener(this);
    }

    @Override
    public Float getViewValue(TextView textView) {
        return Float.parseFloat(textView.getText().toString().trim().replace(",", "."));
    }

    @Override
    public void bindUI(Float aFloat, TextView textView) {
        textView.setText(String.valueOf(aFloat));
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        Float value = Float.parseFloat(editable.toString());
        bindObject(value);
    }
}
