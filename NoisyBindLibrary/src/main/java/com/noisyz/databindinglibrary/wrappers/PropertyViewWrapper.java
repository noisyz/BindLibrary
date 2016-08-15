package com.noisyz.databindinglibrary.wrappers;

import com.noisyz.databindinglibrary.bind.base.AbsUIBinder;
import com.noisyz.databindinglibrary.conversion.Converter;
import com.noisyz.databindinglibrary.conversion.EmptyConverter;
import com.noisyz.databindinglibrary.wrappers.impl.view.AbsViewWrapper;

/**
 * Created by Oleg on 17.03.2016.
 */
public abstract class PropertyViewWrapper<V extends AbsViewWrapper> extends AbsUIBinder implements ObjectBinder, AbsViewWrapper.OnViewValueChangedListener {

    private Converter updateUIConverter = new EmptyConverter(), updateObjectValueConverter = new EmptyConverter();

    private V v;
    private Object object;

    public PropertyViewWrapper(V v, Object object) {
        this.object = object;
        this.v = v;
        this.v.setOnViewValueChangedListener(this);
    }

    @Override
    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public void bindUI() {
        Object value = getUIBindValue();
        if (v != null && value != null) {
            v.bindUI(value);
        }
    }

    protected Object getUIBindValue() {
        return getUpdateUIConverter().getConvertedValue(
                getUIBindPropertyValue()
        );
    }

    protected abstract Object getUIBindPropertyValue();

    protected abstract void updateObjectByValue(Object value);

    @Override
    public void bindObject(Object value) {
        Object convertedValue = getUpdateObjectValueConverter().
                getConvertedValue(value);
        updateObjectByValue(convertedValue);
    }

    public void setUpdateUIConverter(Converter converter) {
        this.updateUIConverter = converter;
    }

    public void setUpdateObjectConverter(Converter converter) {
        this.updateObjectValueConverter = converter;
    }

    public Converter getUpdateUIConverter() {
        return updateUIConverter;
    }

    public Converter getUpdateObjectValueConverter() {
        return updateObjectValueConverter;
    }

    protected Object getObject() {
        return object;
    }

    protected V getViewWrapper() {
        return v;
    }

    @Override
    public void release() {
        super.release();
        this.v.release();
        this.v = null;
        this.object = null;
        this.updateObjectValueConverter = null;
        this.updateUIConverter = null;
    }

    @Override
    public void onViewValueChanged(Object value) {
        bindObject(value);
    }
}
