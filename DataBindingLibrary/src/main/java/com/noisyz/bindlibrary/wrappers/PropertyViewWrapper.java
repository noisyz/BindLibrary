package com.noisyz.bindlibrary.wrappers;

import android.view.View;

import com.noisyz.bindlibrary.base.AbsUIBinder;
import com.noisyz.bindlibrary.conversion.Converter;
import com.noisyz.bindlibrary.conversion.EmptyConverter;
import com.noisyz.bindlibrary.wrappers.impl.ViewBinder;
import com.noisyz.bindlibrary.wrappers.impl.view.IViewBinder;

import java.lang.ref.WeakReference;

/**
 * Created by Oleg on 17.03.2016.
 */
public abstract class PropertyViewWrapper<VB extends IViewBinder> extends AbsUIBinder implements ViewBinder.OnViewValueChangedListener {

    private Converter updateUIConverter = new EmptyConverter(), updateObjectValueConverter = new EmptyConverter();

    private VB vb;
    private WeakReference<View> view;

    public PropertyViewWrapper(VB vb, View view, Object object, String propertyName) {
        super(object, propertyName);
        this.vb = vb;
        this.view = new WeakReference<>(view);
        if (vb instanceof ViewBinder) {
            ViewBinder viewBinder = (ViewBinder) vb;
            viewBinder.addListeners(view);
            viewBinder.setOnViewValueChangedListener(this);
        }
    }

    @Override
    public void bindUI() {

        Object value = getUIBindValue();
        View view = this.view.get();

        if (view != null && value != null) {
            if (vb instanceof ViewBinder) {
                ((ViewBinder) vb).removeListeners(view);
                vb.bindUI(value, view);
                ((ViewBinder) vb).addListeners(view);
            } else
                vb.bindUI(value, view);
        }
    }

    @Override
    public void onViewValueChanged() {
        if (vb instanceof ViewBinder) {
            ViewBinder viewBinder = (ViewBinder) vb;
            View view = this.view.get();
            if (view != null)
                bindObject(viewBinder.getViewValue(view));
        }
    }

    public void bindObject(Object value) {
        if (value != null) {
            Object convertedValue = getUpdateObjectValueConverter().
                    getConvertedValue(value);
            updateObjectByValue(convertedValue);
        }
    }

    protected Object getUIBindValue() {
        return getUpdateUIConverter().getConvertedValue(
                getUIBindPropertyValue()
        );
    }

    protected abstract Object getUIBindPropertyValue();

    protected abstract void updateObjectByValue(Object value);


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

    @Override
    public void release() {
        super.release();
        if (vb instanceof ViewBinder) {
            View view = this.view.get();
            if (view != null)
                ((ViewBinder) vb).removeListeners(view);
            ((ViewBinder) vb).release();
        }
        this.vb = null;
        this.updateObjectValueConverter = null;
        this.updateUIConverter = null;
    }
}
