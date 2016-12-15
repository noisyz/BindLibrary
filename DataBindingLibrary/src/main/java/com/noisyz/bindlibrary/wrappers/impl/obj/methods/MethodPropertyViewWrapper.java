package com.noisyz.bindlibrary.wrappers.impl.obj.methods;

import android.view.View;

import com.noisyz.bindlibrary.utils.ReflectionUtils;
import com.noisyz.bindlibrary.wrappers.impl.obj.PropertyViewWrapper;
import com.noisyz.bindlibrary.wrappers.impl.obj.IViewBinder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Oleg on 17.03.2016.
 */
public class MethodPropertyViewWrapper extends PropertyViewWrapper{

    public static final int GETTER = 0;
    public static final int SETTER = 1;
    private Method setter, getter;

    public MethodPropertyViewWrapper(IViewBinder iViewBinder, View view, Object object, String propertyName, Method getter, Method setter) {
        super(iViewBinder, view, object, propertyName);
        this.getter = getter;
        this.setter = setter;
    }

    public MethodPropertyViewWrapper(IViewBinder iViewBinder, View view, Object object, String propertyName, Method method, int mode) {
        super(iViewBinder, view, object, propertyName);
        switch (mode) {
            case GETTER:
                getter = method;
                break;
            case SETTER:
                setter = method;
                break;
        }
    }

    @Override
    protected Object getUIBindPropertyValue() {
        return getter != null ? ReflectionUtils.invokeGetterMethod(getter, getObject()) : null;
    }

    @Override
    protected void updateObjectByValue(Object value) {
        if (setter != null) {
            try {
                ReflectionUtils.invokeSetterMethod(setter, getObject(), value);
                onObjectUpdated(getObject(), getBinderKey(), value);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void release(){
        super.release();
        setter = null;
        getter = null;
    }
}
