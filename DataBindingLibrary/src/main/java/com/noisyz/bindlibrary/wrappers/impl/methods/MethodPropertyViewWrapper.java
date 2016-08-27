package com.noisyz.bindlibrary.wrappers.impl.methods;

import com.noisyz.bindlibrary.utils.ReflectionUtils;
import com.noisyz.bindlibrary.wrappers.PropertyViewWrapper;
import com.noisyz.bindlibrary.wrappers.impl.view.AbsViewWrapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Oleg on 17.03.2016.
 */
public class MethodPropertyViewWrapper<VW extends AbsViewWrapper> extends PropertyViewWrapper<VW> {

    public static final int GETTER = 0;
    public static final int SETTER = 1;
    private Method setter, getter;

    public MethodPropertyViewWrapper(VW vw, Object object, Method getter, Method setter) {
        super(vw, object);
        this.getter = getter;
        this.setter = setter;
    }

    public MethodPropertyViewWrapper(VW vw, Object object, Method method, int mode) {
        super(vw, object);
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
                onObjectUpdated(getObject(), getPropertyName(), value);
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
