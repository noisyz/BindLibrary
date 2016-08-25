package com.noisyz.databindinglibrary.wrappers.impl.methods;

import com.noisyz.databindinglibrary.utils.ReflectionUtils;
import com.noisyz.bindlibrary.wrappers.PropertyViewWrapper;
import com.noisyz.databindinglibrary.wrappers.impl.view.AbsViewWrapper;

import java.lang.reflect.Method;

/**
 * Created by Oleg on 17.03.2016.
 */
public class MethodPropertyViewWrapper<V extends AbsViewWrapper> extends PropertyViewWrapper<V> {

    public static final int GETTER = 0;
    public static final int SETTER = 1;
    private Method setter, getter;

    public MethodPropertyViewWrapper(V v, Object object, Method getter, Method setter) {
        super(v, object);
        this.getter = getter;
        this.setter = setter;
    }

    public MethodPropertyViewWrapper(V v, Object object, Method method, int mode) {
        super(v, object);
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
        if (setter != null)
            ReflectionUtils.invokeSetterMethod(setter, getObject(), value);
    }

    @Override
    public void release(){
        super.release();
        setter = null;
        getter = null;
    }
}
