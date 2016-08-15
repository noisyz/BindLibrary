package com.noisyz.databindinglibrary.wrappers;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.noisyz.databindinglibrary.annotations.converters.Conversion;
import com.noisyz.databindinglibrary.annotations.converters.ConvertToObject;
import com.noisyz.databindinglibrary.annotations.converters.ConvertToUI;
import com.noisyz.databindinglibrary.annotations.field.CustomFieldType;
import com.noisyz.databindinglibrary.annotations.field.ImageField;
import com.noisyz.databindinglibrary.annotations.field.SimpleAdapterViewField;
import com.noisyz.databindinglibrary.annotations.field.SimpleFieldType;
import com.noisyz.databindinglibrary.annotations.methods.CustomGetterMethod;
import com.noisyz.databindinglibrary.annotations.methods.CustomSetterMethod;
import com.noisyz.databindinglibrary.annotations.methods.GetterMethod;
import com.noisyz.databindinglibrary.annotations.methods.ImageGetterMethod;
import com.noisyz.databindinglibrary.annotations.methods.SetterMethod;
import com.noisyz.databindinglibrary.annotations.methods.SimpleAdapterViewGetter;
import com.noisyz.databindinglibrary.annotations.methods.SimpleAdapterViewSetter;
import com.noisyz.databindinglibrary.annotations.propertyType;
import com.noisyz.databindinglibrary.bind.base.AbsUIBinder;
import com.noisyz.databindinglibrary.conversion.Converter;
import com.noisyz.databindinglibrary.conversion.EmptyConverter;
import com.noisyz.databindinglibrary.conversion.TwoWayConverter;
import com.noisyz.databindinglibrary.exception.GetterMethodNullException;
import com.noisyz.databindinglibrary.utils.ReflectionUtils;
import com.noisyz.databindinglibrary.wrappers.impl.fields.FieldPropertyViewWrapper;
import com.noisyz.databindinglibrary.wrappers.impl.methods.GetterPropertyViewWrapper;
import com.noisyz.databindinglibrary.wrappers.impl.methods.MethodPropertyViewWrapper;
import com.noisyz.databindinglibrary.wrappers.impl.view.AbsViewWrapper;
import com.noisyz.databindinglibrary.wrappers.impl.view.adapterviewwrapper.SimpleAdapterViewWrapper;
import com.noisyz.databindinglibrary.wrappers.impl.view.image.ImageViewWrapper;
import com.noisyz.databindinglibrary.wrappers.impl.view.simple.ChangebleRatingBarWrapper;
import com.noisyz.databindinglibrary.wrappers.impl.view.simple.CompoundButtonWrapper;
import com.noisyz.databindinglibrary.wrappers.impl.view.simple.EnabledWrapper;
import com.noisyz.databindinglibrary.wrappers.impl.view.simple.FloatTextWrapper;
import com.noisyz.databindinglibrary.wrappers.impl.view.simple.ProgressViewWrapper;
import com.noisyz.databindinglibrary.wrappers.impl.view.simple.RatingBarWrapper;
import com.noisyz.databindinglibrary.wrappers.impl.view.simple.SeekBarWrapper;
import com.noisyz.databindinglibrary.wrappers.impl.view.simple.TextViewWrapper;
import com.noisyz.databindinglibrary.wrappers.impl.view.simple.VisibilityWrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Oleg on 17.03.2016.
 */
public class WrapperViewFactory {

    public static PropertyViewWrapper getSimplePropertyViewWrapper(SimpleFieldType fieldType, View view, Object object, Field field) {
        PropertyViewWrapper propertyViewWrapper =
                new FieldPropertyViewWrapper(getViewWrapper(fieldType.value(), view), object, field);
        try {
            setConversion(fieldType.twoWayConverter(), fieldType.convertToObject(), fieldType.convertToUI(), propertyViewWrapper);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return propertyViewWrapper;
    }

    public static PropertyViewWrapper getSimplePropertyViewWrapper(CustomFieldType fieldType, View view, Object object, Field field) {
        PropertyViewWrapper propertyViewWrapper = new FieldPropertyViewWrapper(
                getCustomViewWrapper(fieldType.customViewWrapper(), view), object, field);
        try {
            setConversion(fieldType.twoWayConverter(), fieldType.convertToObject(), fieldType.convertToUI(), propertyViewWrapper);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return propertyViewWrapper;
    }

    private static AbsViewWrapper getCustomViewWrapper(Class<? extends AbsViewWrapper> clazz, View view){
        return (AbsViewWrapper) ReflectionUtils.getClassInstance(clazz, view);
    }

    public static PropertyViewWrapper getSimplePropertyViewWrapper(GetterMethod getterMethod, SetterMethod setterMethod, View view, Object object, Method getter, Method setter) {
        if(getterMethod == null)
            throw new GetterMethodNullException();
        PropertyViewWrapper propertyViewWrapper =
                new MethodPropertyViewWrapper(getViewWrapper(getterMethod.value(), view), object, getter, setter);
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
        if(getterMethod == null)
            throw new GetterMethodNullException();
        PropertyViewWrapper propertyViewWrapper =
                new MethodPropertyViewWrapper(getCustomViewWrapper(getterMethod.customViewWrapper(), view), object, getter, setter);
        try {
            setConversion(null, setterMethod.convertToObject(), getterMethod.convertToUI(), propertyViewWrapper);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return propertyViewWrapper;
    }

    private static AbsViewWrapper getViewWrapper(propertyType type, View view) {
        AbsViewWrapper viewWrapper = null;
        try {
            switch (type) {
                case BOOLEAN:
                    if (view instanceof CompoundButton)
                        viewWrapper = new CompoundButtonWrapper((CompoundButton) view);
                    else viewWrapper = new VisibilityWrapper(view);
                    break;
                case TEXT:
                    viewWrapper = new TextViewWrapper((TextView) view);
                    break;
                case PROGRESS:
                    viewWrapper = new ProgressViewWrapper((ProgressBar) view);
                    break;
                case PROGRESS_CHANGEBLE:
                    viewWrapper = new SeekBarWrapper((SeekBar) view);
                    break;
                case RATING:
                    viewWrapper = new RatingBarWrapper((RatingBar) view);
                    break;
                case RATING_CHANGEBLE:
                    viewWrapper = new ChangebleRatingBarWrapper((RatingBar) view);
                    break;
                case VISIBILITY:
                    viewWrapper = new VisibilityWrapper(view);
                    break;
                case FLOAT_TEXT:
                    viewWrapper = new FloatTextWrapper((TextView) view);
                    break;
                case ENABLED:
                    viewWrapper = new EnabledWrapper(view);
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

    public static PropertyViewWrapper getImagePropertyViewWrapper(ImageField fieldType, View view, Object object, Field field) {
        try {
            AbsViewWrapper viewWrapper = new ImageViewWrapper(fieldType.imageProvider().newInstance(), view);
            PropertyViewWrapper propertyViewWrapper = new FieldPropertyViewWrapper(viewWrapper, object, field);
            return propertyViewWrapper;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PropertyViewWrapper getImagePropertyViewWrapper(ImageGetterMethod fieldType, View view, Object object, Method method) {
        try {
            AbsViewWrapper viewWrapper = new ImageViewWrapper(fieldType.imageProvider().newInstance(), view);
            PropertyViewWrapper propertyViewWrapper = new GetterPropertyViewWrapper(viewWrapper, object, method);
            return propertyViewWrapper;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PropertyViewWrapper getSimpleAdapterViewWrapper(SimpleAdapterViewField fieldType, View view, Object object, Field field) {
        int indent = fieldType.indent();
        String[] values = view.getContext().getResources().getStringArray(fieldType.resourceArray());
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(view.getContext(), fieldType.layoutResID(), values);
        ((AdapterView) view).setAdapter(spinnerArrayAdapter);
        SimpleAdapterViewWrapper wrapper = new SimpleAdapterViewWrapper((AdapterView) view, indent);
        PropertyViewWrapper fieldPropertyViewWrapper = new FieldPropertyViewWrapper(wrapper, object, field);
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
        SimpleAdapterViewWrapper wrapper = new SimpleAdapterViewWrapper((AdapterView) view, indent);
        PropertyViewWrapper fieldPropertyViewWrapper = new MethodPropertyViewWrapper(wrapper, object, getter, setter);
        return fieldPropertyViewWrapper;
    }
}
