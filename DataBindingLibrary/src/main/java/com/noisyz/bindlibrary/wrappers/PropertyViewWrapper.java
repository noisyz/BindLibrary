package com.noisyz.bindlibrary.wrappers;

import com.noisyz.bindlibrary.base.AbsUIBinder;
import com.noisyz.bindlibrary.conversion.Converter;
import com.noisyz.bindlibrary.conversion.EmptyConverter;
import com.noisyz.bindlibrary.wrappers.impl.view.AbsViewWrapper;

/**
 * Created by Oleg on 17.03.2016.
 */
public abstract class PropertyViewWrapper<VW extends AbsViewWrapper> extends AbsUIBinder implements ObjectBinder, AbsViewWrapper.OnViewValueChangedListener {

    private Converter updateUIConverter = new EmptyConverter(), updateObjectValueConverter = new EmptyConverter();

    private VW vw;
    private String propertyName;

    public PropertyViewWrapper(VW vw, Object object) {
        super(object);
        this.vw = vw;
        this.vw.setOnViewValueChangedListener(this);
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public void bindUI() {
        Object value = getUIBindValue();
        if (vw != null && value != null) {
            vw.bindUI(value);
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
        if (convertedValue != null)
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

    protected VW getViewWrapper() {
        return vw;
    }

    @Override
    public void release() {
        super.release();
        this.vw.release();
        this.vw = null;
        this.updateObjectValueConverter = null;
        this.updateUIConverter = null;
    }

    @Override
    public void onViewValueChanged(Object value) {
        bindObject(value);
    }
}
