package com.noisyz.databindinglibrary.wrappers.impl.view.simple;

import android.text.InputType;
import android.widget.TextView;

import com.noisyz.databindinglibrary.wrappers.ObjectBinder;

/**
 * Created by Oleg on 18.03.2016.
 */
public class FloatTextWrapper extends TextViewWrapper {
    public FloatTextWrapper(TextView textView) {
        super(textView);
        textView.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
    }
}
