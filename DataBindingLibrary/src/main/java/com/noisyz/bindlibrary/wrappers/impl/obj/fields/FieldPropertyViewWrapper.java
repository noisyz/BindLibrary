package com.noisyz.bindlibrary.wrappers.impl.obj.fields;

import android.view.View;

import com.noisyz.bindlibrary.utils.ReflectionUtils;
import com.noisyz.bindlibrary.wrappers.impl.obj.PropertyViewWrapper;
import com.noisyz.bindlibrary.wrappers.impl.obj.IViewBinder;

import java.lang.reflect.Field;

/**
 * Created by Oleg on 17.03.2016.
 */
public class FieldPropertyViewWrapper extends PropertyViewWrapper {

    private Field field;

    public FieldPropertyViewWrapper(IViewBinder iViewBinder, View view, Object object, Field field) {
        super(iViewBinder, view, object, field.getName());
        this.field = field;
    }

    @Override
    protected Object getUIBindPropertyValue() {
        return ReflectionUtils.getVariableValue(getField(), getObject());
    }

    @Override
    protected void updateObjectByValue(Object value) {
        try {
            ReflectionUtils.setVariableValue(field, getObject(), value);
            onObjectUpdated(getObject(), field.getName(), value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void release(){
        super.release();
        field = null;
    }

    protected Field getField() {
        return field;
    }

    public String getFieldName() {
        return field.getName();
    }
}
