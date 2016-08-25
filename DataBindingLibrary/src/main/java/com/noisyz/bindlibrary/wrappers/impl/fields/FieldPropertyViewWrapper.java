package com.noisyz.databindinglibrary.wrappers.impl.fields;

import com.noisyz.databindinglibrary.utils.ReflectionUtils;
import com.noisyz.bindlibrary.wrappers.PropertyViewWrapper;
import com.noisyz.databindinglibrary.wrappers.impl.view.AbsViewWrapper;

import java.lang.reflect.Field;

/**
 * Created by Oleg on 17.03.2016.
 */
public class FieldPropertyViewWrapper<V extends AbsViewWrapper> extends PropertyViewWrapper<V> {

    private Field field;

    public FieldPropertyViewWrapper(V v, Object object, Field field) {
        super(v, object);
        this.field = field;
    }

    @Override
    protected Object getUIBindPropertyValue() {
        return ReflectionUtils.getVariableValue(getField(), getObject());
    }

    @Override
    protected void updateObjectByValue(Object value) {
        ReflectionUtils.setVariableValue(field, getObject(), value);
        onObjectUpdated(getObject(), field.getName(), value);
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
