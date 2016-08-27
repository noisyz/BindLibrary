package com.noisyz.bindlibrary.wrappers.impl.view.simple;

import android.text.InputType;
import android.widget.TextView;

/**
 * Created by Oleg on 18.03.2016.
 */
public class FloatTextWrapper extends TextViewWrapper {
    public FloatTextWrapper(TextView textView) {
        super(textView);
        textView.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
    }
}
