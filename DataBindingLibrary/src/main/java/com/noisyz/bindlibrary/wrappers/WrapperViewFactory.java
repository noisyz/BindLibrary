package com.noisyz.bindlibrary.wrappers;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.noisyz.bindlibrary.annotations.converters.Conversion;
import com.noisyz.bindlibrary.annotations.converters.ConvertToObject;
import com.noisyz.bindlibrary.annotations.converters.ConvertToUI;
import com.noisyz.bindlibrary.annotations.field.CustomField;
import com.noisyz.bindlibrary.annotations.field.ImageField;
import com.noisyz.bindlibrary.annotations.field.AdapterViewField;
import com.noisyz.bindlibrary.annotations.methods.CustomGetterMethod;
import com.noisyz.bindlibrary.annotations.methods.CustomSetterMethod;
import com.noisyz.bindlibrary.annotations.methods.GetterMethod;
import com.noisyz.bindlibrary.annotations.methods.ImageGetterMethod;
import com.noisyz.bindlibrary.annotations.methods.SetterMethod;
import com.noisyz.bindlibrary.annotations.methods.SimpleAdapterViewGetter;
import com.noisyz.bindlibrary.annotations.methods.SimpleAdapterViewSetter;
import com.noisyz.bindlibrary.annotations.propertyType;
import com.noisyz.bindlibrary.conversion.Converter;
import com.noisyz.bindlibrary.conversion.EmptyConverter;
import com.noisyz.bindlibrary.conversion.TwoWayConverter;
import com.noisyz.bindlibrary.exception.GetterMethodNullException;
import com.noisyz.bindlibrary.wrappers.impl.fields.FieldPropertyViewWrapper;
import com.noisyz.bindlibrary.wrappers.impl.methods.GetterPropertyViewWrapper;
import com.noisyz.bindlibrary.wrappers.impl.methods.MethodPropertyViewWrapper;
import com.noisyz.bindlibrary.wrappers.impl.view.IViewBinder;
import com.noisyz.bindlibrary.wrappers.impl.view.adapterviewwrapper.SimpleAdapterViewWrapper;
import com.noisyz.bindlibrary.wrappers.impl.view.image.ImageViewWrapper;
import com.noisyz.bindlibrary.wrappers.impl.view.simple.ChangeableRatingBarWrapper;
import com.noisyz.bindlibrary.wrappers.impl.view.simple.ChildObjectViewWrapper;
import com.noisyz.bindlibrary.wrappers.impl.view.simple.CompoundButtonWrapper;
import com.noisyz.bindlibrary.wrappers.impl.view.simple.DoubleTextWrapper;
import com.noisyz.bindlibrary.wrappers.impl.view.simple.EnabledWrapper;
import com.noisyz.bindlibrary.wrappers.impl.view.simple.FloatTextWrapper;
import com.noisyz.bindlibrary.wrappers.impl.view.simple.ProgressViewWrapper;
import com.noisyz.bindlibrary.wrappers.impl.view.simple.RatingBarWrapper;
import com.noisyz.bindlibrary.wrappers.impl.view.simple.ResourceTextViewWrapper;
import com.noisyz.bindlibrary.wrappers.impl.view.simple.SeekBarWrapper;
import com.noisyz.bindlibrary.wrappers.impl.view.simple.TextViewWrapper;
import com.noisyz.bindlibrary.wrappers.impl.view.simple.VisibilityWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Oleg on 17.03.2016.
 */
public class WrapperViewFactory {

    public static PropertyViewWrapper getSimplePropertyViewWrapper(com.noisyz.bindlibrary.annotations.field.Field fieldType, View view, Object object, Field field) {
        PropertyViewWrapper propertyViewWrapper = new FieldPropertyViewWrapper(getViewWrapper(fieldType.value(), view), view, object, field);
        try {
            setConversion(fieldType.twoWayConverter(), fieldType.convertToObject(), fieldType.convertToUI(), propertyViewWrapper);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return propertyViewWrapper;
    }

    public static PropertyViewWrapper getSimplePropertyViewWrapper(CustomField fieldType, View view, Object object, Field field) {
        IViewBinder iViewBinder = getCustomViewWrapper(fieldType.value());
        if (iViewBinder != null) {
            PropertyViewWrapper propertyViewWrapper = new FieldPropertyViewWrapper(
                    getCustomViewWrapper(fieldType.value()), view, object, field);
            try {
                setConversion(fieldType.twoWayConverter(), fieldType.convertToObject(), fieldType.convertToUI(), propertyViewWrapper);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return propertyViewWrapper;
        }
        return null;
    }

    private static IViewBinder getCustomViewWrapper(Class<? extends IViewBinder> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PropertyViewWrapper getSimplePropertyViewWrapper(GetterMethod getterMethod, SetterMethod setterMethod, View view, Object object, Method getter, Method setter) {
        if (getterMethod == null)
            throw new GetterMethodNullException();
        PropertyViewWrapper propertyViewWrapper =
                new MethodPropertyViewWrapper(getViewWrapper(getterMethod.value(), view), view, object, getter, setter);
        try {
            setConversion(null, setterMethod.convertToObject(), getterMethod.convertToUI(), propertyViewWrapper);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return propertyViewWrapper;
    }

    public static PropertyViewWrapper getSimplePropertyViewWrapper(CustomGetterMethod getterMethod, CustomSetterMethod setterMethod, View view, Object object, Method getter, Method setter) {
        if (getterMethod == null)
            throw new GetterMethodNullException();
        IViewBinder iViewBinder = getCustomViewWrapper(getterMethod.value());
        if (iViewBinder != null) {
            PropertyViewWrapper propertyViewWrapper = new MethodPropertyViewWrapper(iViewBinder, view, object, getter, setter);
            try {
                setConversion(null, setterMethod.convertToObject(), getterMethod.convertToUI(), propertyViewWrapper);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return propertyViewWrapper;
        }
        return null;
    }

    private static IViewBinder getViewWrapper(propertyType type, View view) {
        IViewBinder viewWrapper = null;
        try {
            switch (type) {
                case BOOLEAN:
                    if (view instanceof CompoundButton)
                        viewWrapper = new CompoundButtonWrapper();
                    else viewWrapper = new VisibilityWrapper();
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
                case OBJECT:
                    viewWrapper = new ChildObjectViewWrapper();
                    break;
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
            return null;
        }
        return viewWrapper;
    }

    private static void setConversion(Conversion conversion, ConvertToObject convertToObject, ConvertToUI convertToUI, PropertyViewWrapper viewWrapper) throws InstantiationException, IllegalAccessException {
        TwoWayConverter converter = conversion.value().newInstance();
        if (!(converter instanceof EmptyConverter)) {
            viewWrapper.setUpdateUIConverter(converter.getConverterToUi());
            viewWrapper.setUpdateObjectConverter(converter.getConverterToObject());
        } else {
            if (convertToObject != null) {
                Converter converterToObject = convertToObject.value().newInstance();
                viewWrapper.setUpdateObjectConverter(converterToObject);
            }

            if (convertToUI != null) {
                Converter converterToUI = convertToUI.value().newInstance();
                viewWrapper.setUpdateUIConverter(converterToUI);
            }
        }

    }

    public static PropertyViewWrapper getImagePropertyViewWrapper(ImageField fieldType, ImageView view, Object object, Field field) {
        try {
            IViewBinder viewWrapper = new ImageViewWrapper(fieldType.value().newInstance());
            PropertyViewWrapper propertyViewWrapper = new FieldPropertyViewWrapper(viewWrapper, view, object, field);
            return propertyViewWrapper;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PropertyViewWrapper getImagePropertyViewWrapper(ImageGetterMethod fieldType, ImageView view, Object object, Method method) {
        try {
            IViewBinder viewWrapper = new ImageViewWrapper(fieldType.value().newInstance());
            PropertyViewWrapper propertyViewWrapper = new GetterPropertyViewWrapper(viewWrapper, view, object, method);
            return propertyViewWrapper;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PropertyViewWrapper getSimpleAdapterViewWrapper(AdapterViewField fieldType, View view, Object object, Field field) {
        int indent = fieldType.indent();
        String[] values = view.getContext().getResources().getStringArray(fieldType.resourceArray());
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(view.getContext(), fieldType.layoutResID(), values);
        ((AdapterView) view).setAdapter(spinnerArrayAdapter);
        SimpleAdapterViewWrapper wrapper = new SimpleAdapterViewWrapper(indent);
        PropertyViewWrapper fieldPropertyViewWrapper = new FieldPropertyViewWrapper(wrapper, view, object, field);
        return fieldPropertyViewWrapper;
    }

    public static PropertyViewWrapper getSimpleAdapterViewWrapper(SimpleAdapterViewGetter getterType,
                                                                  SimpleAdapterViewSetter setterType,
                                                                  AdapterView view, Object object, Method getter, Method setter) {

        int indent = 0, layoutResID = 0, resourceArray = 0, indentGetter = 0, indentSetter = 0,
                getterLayoutResId = 0, setterLayoutResId = 0, getterArray = 0, setterArray = 0;


        if (getterType != null) {
            indentGetter = getterType.indent();
            getterLayoutResId = getterType.layoutResID();
            getterArray = getterType.resourceArray();
        }
        if (setterType != null) {
            indentSetter = setterType.indent();
            setterLayoutResId = setterType.layoutResID();
            setterArray = setterType.resourceArray();
        }
        indent = indentGetter != 0 ? indentGetter : indentSetter != 0 ? indentSetter : 0;
        layoutResID = getterLayoutResId != 0 ? getterLayoutResId : setterLayoutResId != 0 ? setterLayoutResId : 0;
        resourceArray = getterArray != 0 ? getterArray : setterArray != 0 ? setterArray : 0;
        String[] values = view.getContext().getResources().getStringArray(resourceArray);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(view.getContext(), layoutResID, values);
        view.setAdapter(spinnerArrayAdapter);
        SimpleAdapterViewWrapper wrapper = new SimpleAdapterViewWrapper(indent);
        PropertyViewWrapper fieldPropertyViewWrapper = new MethodPropertyViewWrapper(wrapper, view, object, getter, setter);
        return fieldPropertyViewWrapper;
    }
}
