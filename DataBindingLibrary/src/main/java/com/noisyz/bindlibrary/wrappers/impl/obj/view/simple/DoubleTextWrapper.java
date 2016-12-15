package com.noisyz.bindlibrary.wrappers.impl.obj.view.simple;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.TextView;

import com.noisyz.bindlibrary.wrappers.impl.obj.ViewBinder;

/**
 * Created by Oleg on 18.03.2016.
 */
public class DoubleTextWrapper extends ViewBinder<Double, TextView> implements TextWatcher{

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
    public Double getViewValue(TextView textView) {
        return Double.parseDouble(textView.getText().toString().trim().replace(",", "."));
    }

    @Override
    public void bindUI(Double aDouble, TextView textView) {
        textView.setText(String.valueOf(aDouble));
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        bindObject();
    }
}
