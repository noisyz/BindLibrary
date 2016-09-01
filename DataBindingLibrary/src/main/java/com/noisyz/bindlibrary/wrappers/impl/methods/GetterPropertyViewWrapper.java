package com.noisyz.bindlibrary.wrappers.impl.methods;

import android.view.View;

import com.noisyz.bindlibrary.wrappers.impl.view.IViewBinder;

import java.lang.reflect.Method;

/**
 * Created by Oleg on 17.03.2016.
 */
public class GetterPropertyViewWrapper extends MethodPropertyViewWrapper {

    public GetterPropertyViewWrapper(IViewBinder iViewBinder, View view, Object object, Method method) {
        super(iViewBinder, view, object, method, GETTER);
    }
}
