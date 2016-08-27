package com.noisyz.bindlibrary.base.property;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

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
import com.noisyz.bindlibrary.base.AbsUIBinder;
import com.noisyz.bindlibrary.wrappers.PropertyViewWrapper;
import com.noisyz.bindlibrary.wrappers.WrapperViewFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Oleg on 05.04.2016.
 */
public class PropertyFactory {

    public static ArrayList<Property> getPropertyList(AbsUIBinder parentBinder, Object object, View parentView) {
        ArrayList<Property> properties = new ArrayList<>();
        if (object != null && parentView != null) {
            properties.addAll(getFieldPropertyList(parentBinder, object, parentView));
            properties.addAll(getMethodPropertyList(parentBinder, object, parentView));
        }
        return properties;
    }

    private static ArrayList<Property> getMethodPropertyList(AbsUIBinder parentBinder, Object object, View parentView) {
        ArrayList<Property> methodList = new ArrayList<>();
        for (Method method : object.getClass().getDeclaredMethods()) {
            methodList.addAll(getImageGetterMethodPropertyList(method, object, parentView));
            methodList.addAll(getSimpleGetterMethodPropertyList(parentBinder, method, object, parentView));
            methodList.addAll(getCustomGetterMethodPropertyList(parentBinder, method, object, parentView));
            methodList.addAll(getSimpleAdapterGetterMethodPropertyList(parentBinder, method, object, parentView));
        }
        return methodList;
    }

    private static ArrayList<Property> getSimpleAdapterGetterMethodPropertyList
            (AbsUIBinder parentBinder, Method getter, Object object, View parentView) {
        ArrayList<Property> properties = new ArrayList<>();
        SimpleAdapterViewGetter getterMethod = getter.getAnnotation(SimpleAdapterViewGetter.class);
        if (getterMethod != null) {
            String propertyKey = getterMethod.propertyKey();
            if (propertyKey.isEmpty()) {
                propertyKey = parentView.getContext().getString(getterMethod.propertyKeyResId());
            }
            if (!propertyKey.isEmpty()) {
                Method setter = getAdapterSetterMethod(object, propertyKey);
                SimpleAdapterViewSetter setterMethod = null;
                if (setter != null) {
                    setterMethod = setter.getAnnotation(SimpleAdapterViewSetter.class);
                }
                ArrayList<View> views = getViewsByTag((ViewGroup) parentView, propertyKey);
                for (View view : views) {
                    PropertyViewWrapper viewWrapper = WrapperViewFactory.
                            getSimpleAdapterViewWrapper(getterMethod, setterMethod,
                                    (AdapterView) view, object, getter, setter);
                    if (viewWrapper != null) {
                        properties.add(new Property(propertyKey, viewWrapper));
                        if (!viewWrapper.hasDataUpdatedCallback() && parentBinder.hasDataUpdatedCallback()) {
                            viewWrapper.setDataUpdatedCallback(parentBinder.getDataUpdatedCallback());
                        }
                    }
                }
            }
        }
        return properties;
    }

    private static Method getAdapterSetterMethod(Object object, String propertyKey) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            SimpleAdapterViewSetter setterMethod = method.getAnnotation(SimpleAdapterViewSetter.class);
            if (setterMethod != null && setterMethod.propertyKey().equals(propertyKey)) {
                return method;
            }
        }
        return null;
    }


    private static ArrayList<Property> getSimpleGetterMethodPropertyList(AbsUIBinder parentBinder, Method getter, Object object, View parentView) {
        ArrayList<Property> properties = new ArrayList<>();
        GetterMethod getterMethod = getter.getAnnotation(GetterMethod.class);
        if (getterMethod != null) {
            String propertyKey = getterMethod.propertyKey();
            if (propertyKey.isEmpty()) {
                propertyKey = parentView.getContext().getString(getterMethod.propertyKeyResId());
            }
            if (!propertyKey.isEmpty()) {
                Method setter = getSetterMethod(object, propertyKey);
                SetterMethod setterMethod = null;
                if (setter != null) {
                    setterMethod = setter.getAnnotation(SetterMethod.class);
                }
                ArrayList<View> views = getViewsByTag((ViewGroup) parentView, propertyKey);
                for (View view : views) {
                    PropertyViewWrapper viewWrapper = WrapperViewFactory.
                            getSimplePropertyViewWrapper(getterMethod, setterMethod, view, object, getter, setter);
                    if (viewWrapper != null) {
                        properties.add(new Property(propertyKey, viewWrapper));
                        if (!viewWrapper.hasDataUpdatedCallback() && parentBinder.hasDataUpdatedCallback()) {
                            viewWrapper.setDataUpdatedCallback(parentBinder.getDataUpdatedCallback());
                        }
                    }
                }
            }
        }
        return properties;
    }

    private static ArrayList<Property> getCustomGetterMethodPropertyList(AbsUIBinder parentBinder, Method getter, Object object, View parentView) {
        ArrayList<Property> properties = new ArrayList<>();
        CustomGetterMethod getterMethod = getter.getAnnotation(CustomGetterMethod.class);
        if (getterMethod != null) {
            String propertyKey = getterMethod.propertyKey();
            if (propertyKey.isEmpty()) {
                propertyKey = parentView.getContext().getString(getterMethod.propertyKeyResId());
            }
            if (!propertyKey.isEmpty()) {
                Method setter = getCustomSetterMethod(object, propertyKey);
                CustomSetterMethod setterMethod = null;
                if (setter != null) {
                    setterMethod = setter.getAnnotation(CustomSetterMethod.class);
                }
                ArrayList<View> views = getViewsByTag((ViewGroup) parentView, propertyKey);
                for (View view : views) {
                    PropertyViewWrapper viewWrapper = WrapperViewFactory.
                            getSimplePropertyViewWrapper(getterMethod, setterMethod, view, object, getter, setter);
                    if (viewWrapper != null) {
                        properties.add(new Property(propertyKey, viewWrapper));
                        if (!viewWrapper.hasDataUpdatedCallback() && parentBinder.hasDataUpdatedCallback()) {
                            viewWrapper.setDataUpdatedCallback(parentBinder.getDataUpdatedCallback());
                        }
                    }
                }
            }
        }
        return properties;
    }

    private static Method getSetterMethod(Object object, String propertyKey) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            SetterMethod setterMethod = method.getAnnotation(SetterMethod.class);
            if (setterMethod != null && setterMethod.propertyKey().equals(propertyKey)) {
                return method;
            }
        }
        return null;
    }

    private static Method getCustomSetterMethod(Object object, String propertyKey) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            SetterMethod setterMethod = method.getAnnotation(SetterMethod.class);
            if (setterMethod != null && setterMethod.propertyKey().equals(propertyKey)) {
                return method;
            }
        }
        return null;
    }

    private static ArrayList<Property> getImageGetterMethodPropertyList(Method method, Object object, View parentView) {
        ArrayList<Property> properties = new ArrayList<>();
        ImageGetterMethod imageGetterMethod = method.getAnnotation(ImageGetterMethod.class);
        if (imageGetterMethod != null) {
            String propertyKey = imageGetterMethod.propertyKey();
            if (propertyKey.isEmpty()) {
                propertyKey = parentView.getContext().getString(imageGetterMethod.propertyKeyResId());
            }
            if (!propertyKey.isEmpty()) {
                ArrayList<View> views = getViewsByTag((ViewGroup) parentView, propertyKey);
                for (View view : views) {
                    if (view instanceof ImageView) {
                        PropertyViewWrapper viewWrapper = WrapperViewFactory.getImagePropertyViewWrapper(imageGetterMethod, (ImageView) view, object, method);
                        if (viewWrapper != null) {
                            properties.add(new Property(propertyKey, viewWrapper));
                        }
                    }
                }
            }
        }
        return properties;
    }

    private static ArrayList<Property> getFieldPropertyList(AbsUIBinder parentBinder, Object object, View parentView) {
        ArrayList<Property> fieldList = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            ArrayList<View> views = getViewsByTag((ViewGroup) parentView, field.getName());
            for (View view : views) {
                PropertyViewWrapper viewWrapper = getFieldViewWrapper(parentBinder, field, view, object);
                if (viewWrapper != null) {
                    fieldList.add(new Property(field.getName(), viewWrapper));
                }
            }
        }
        return fieldList;
    }

    private static PropertyViewWrapper getFieldViewWrapper(AbsUIBinder parentBinder, Field objectField, View fieldView, Object o) {
        PropertyViewWrapper viewWrapper = null;
        com.noisyz.bindlibrary.annotations.field.Field field = objectField.getAnnotation(com.noisyz.bindlibrary.annotations.field.Field.class);
        if (field != null) {
            viewWrapper = WrapperViewFactory.getSimplePropertyViewWrapper(field, fieldView, o, objectField);
        }

        ImageField imageField = objectField.getAnnotation(ImageField.class);
        if (imageField != null && fieldView instanceof ImageView) {
            viewWrapper = WrapperViewFactory.getImagePropertyViewWrapper(imageField, (ImageView) fieldView, o, objectField);
        }

        AdapterViewField AdapterViewField = objectField.getAnnotation(AdapterViewField.class);
        if (AdapterViewField != null) {
            viewWrapper = WrapperViewFactory.
                    getSimpleAdapterViewWrapper(AdapterViewField, fieldView, o, objectField);
        }

        CustomField CustomField = objectField.getAnnotation(CustomField.class);
        if (CustomField != null) {
            viewWrapper = WrapperViewFactory.getSimplePropertyViewWrapper(CustomField, fieldView, o, objectField);
        }

        if (viewWrapper != null) {
            if (!viewWrapper.hasDataUpdatedCallback() && parentBinder.hasDataUpdatedCallback()) {
                viewWrapper.setDataUpdatedCallback(parentBinder.getDataUpdatedCallback());
            }
        }
        return viewWrapper;
    }

    private static ArrayList<View> getViewsByTag(ViewGroup root, String tag) {
        ArrayList<View> views = new ArrayList<>();
        if (root != null) {
            final int childCount = root.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = root.getChildAt(i);
                if (child instanceof ViewGroup) {
                    views.addAll(getViewsByTag((ViewGroup) child, tag));
                }
                final Object object = child.getTag();
                if (object != null) {
                    String objectTagInString = object.toString();
                    final String[] tagsObj = objectTagInString.split("\\|");
                    String newTag = "";
                    for (String tagObj : tagsObj) {
                        boolean onlyOne = !tagObj.contains("#");
                        boolean isField = onlyOne ? tagObj.equals(tag) : tagObj.contains(tag);
                        if (!TextUtils.isEmpty(tagObj) && isField) {
                            views.add(child);
                        }else{
                            newTag+=tagObj+"|";
                        }
                    }
                    if(TextUtils.isEmpty(newTag)){
                        child.setTag(null);
                    }else{
                        child.setTag(newTag.substring(0, newTag.length()-1));
                    }
                }
            }
        }
        return views;
    }

}
