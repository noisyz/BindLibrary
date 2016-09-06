package com.noisyz.bindlibrary.wrappers.impl.methods;

import android.view.View;

import com.noisyz.bindlibrary.wrappers.impl.view.IViewBinder;

import java.lang.reflect.Method;

/**
 * Created by Oleg on 17.03.2016.
 */
public class SetterPropertyViewWrapper extends MethodPropertyViewWrapper {

    public SetterPropertyViewWrapper(IViewBinder iViewBinder, View view, Object object, String propertyName, Method method) {
        super(iViewBinder, view, object, propertyName, method, SETTER);
    }
}
