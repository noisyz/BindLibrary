package com.noisyz.bindlibrary.property;

import android.view.View;

import com.noisyz.bindlibrary.annotation.Type;
import com.noisyz.bindlibrary.models.key.Key;
import com.noisyz.bindlibrary.property.abs.Property;
import com.noisyz.bindlibrary.wrappers.IViewBinder;
import com.noisyz.bindlibrary.wrappers.PropertyViewWrapper;
import com.noisyz.bindlibrary.wrappers.ValueProvider;
import com.noisyz.bindlibrary.wrappers.view.simple.BooleanWrapper;
import com.noisyz.bindlibrary.wrappers.view.simple.ChangeableRatingBarWrapper;
import com.noisyz.bindlibrary.wrappers.view.simple.DoubleTextWrapper;
import com.noisyz.bindlibrary.wrappers.view.simple.EnabledWrapper;
import com.noisyz.bindlibrary.wrappers.view.simple.FloatTextWrapper;
import com.noisyz.bindlibrary.wrappers.view.simple.ProgressViewWrapper;
import com.noisyz.bindlibrary.wrappers.view.simple.RatingBarWrapper;
import com.noisyz.bindlibrary.wrappers.view.simple.ResourceTextViewWrapper;
import com.noisyz.bindlibrary.wrappers.view.simple.SeekBarWrapper;
import com.noisyz.bindlibrary.wrappers.view.simple.TextViewWrapper;
import com.noisyz.bindlibrary.wrappers.view.simple.VisibilityWrapper;

/**
 * Created by nero232 on 15.04.17.
 */

public class DefaultProperty<BO, V extends View, T> extends Property<BO, T> {

    private Type type;

    public DefaultProperty(Type type, ValueProvider<BO, T> valueProvider, Key key) {
        super(valueProvider, key);
        this.type = type;
    }

    @Override
    public PropertyViewWrapper<BO, V, T> buildPropertyViewWrapper(BO bo) {
        IViewBinder<T, V> viewBinder = getViewBinder();
        if (viewBinder != null)
            return new PropertyViewWrapper<>(viewBinder, getValueProvider(), bo, getKey());
        return null;
    }

    private IViewBinder<T, V> getViewBinder() {
        IViewBinder viewWrapper = null;
        try {
            switch (type) {
                case BOOLEAN:
                    viewWrapper = new BooleanWrapper();
                    break;
                case TEXT:
                    viewWrapper = new TextViewWrapper();
                    break;
                case TEXT_RES:
                    viewWrapper = new ResourceTextViewWrapper();
                    break;
                case DOUBLE_TEXT:
                    viewWrapper = new DoubleTextWrapper();
                    break;
                case FLOAT_TEXT:
                    viewWrapper = new FloatTextWrapper();
                    break;
                case PROGRESS:
                    viewWrapper = new ProgressViewWrapper();
                    break;
                case PROGRESS_CHANGEABLE:
                    viewWrapper = new SeekBarWrapper();
                    break;
                case RATING:
                    viewWrapper = new RatingBarWrapper();
                    break;
                case RATING_CHANGEABLE:
                    viewWrapper = new ChangeableRatingBarWrapper();
                    break;
                case VISIBILITY:
                    viewWrapper = new VisibilityWrapper();
                    break;
                case ENABLED:
                    viewWrapper = new EnabledWrapper();
                    break;
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
            return null;
        }
        return viewWrapper;
    }
}
