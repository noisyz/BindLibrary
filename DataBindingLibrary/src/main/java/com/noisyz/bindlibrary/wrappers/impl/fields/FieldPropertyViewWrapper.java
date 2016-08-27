package com.noisyz.bindlibrary.wrappers.impl.fields;

import com.noisyz.bindlibrary.utils.ReflectionUtils;
import com.noisyz.bindlibrary.wrappers.PropertyViewWrapper;
import com.noisyz.bindlibrary.wrappers.impl.view.AbsViewWrapper;

import java.lang.reflect.Field;

/**
 * Created by Oleg on 17.03.2016.
 */
public class FieldPropertyViewWrapper<VW extends AbsViewWrapper> extends PropertyViewWrapper<VW> {

    private Field field;

    public FieldPropertyViewWrapper(VW vw, Object object, Field field) {
        super(vw, object);
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
