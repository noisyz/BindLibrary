package com.noisyz.bindlibrary.wrappers;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.noisyz.bindlibrary.annotations.field.collection.AdapterViewField;
import com.noisyz.bindlibrary.annotations.field.simple.CustomField;
import com.noisyz.bindlibrary.annotations.field.simple.ImageField;
import com.noisyz.bindlibrary.annotations.field.simple.ObjectField;
import com.noisyz.bindlibrary.annotations.methods.simple.CustomGetterMethod;
import com.noisyz.bindlibrary.annotations.methods.simple.CustomSetterMethod;
import com.noisyz.bindlibrary.annotations.methods.simple.GetterMethod;
import com.noisyz.bindlibrary.annotations.methods.simple.ImageGetterMethod;
import com.noisyz.bindlibrary.annotations.methods.simple.ObjectGetterMethod;
import com.noisyz.bindlibrary.annotations.methods.simple.SetterMethod;
import com.noisyz.bindlibrary.annotations.methods.collection.SimpleAdapterViewGetter;
import com.noisyz.bindlibrary.annotations.methods.collection.SimpleAdapterViewSetter;
import com.noisyz.bindlibrary.base.AbsUIBinder;
import com.noisyz.bindlibrary.base.ParentBinder;
import com.noisyz.bindlibrary.base.TreeUIBinder;
import com.noisyz.bindlibrary.base.impl.ObjectViewBinder;
import com.noisyz.bindlibrary.utils.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleg on 05.04.2016.
 */
public class PropertyFactory {

    public static List<AbsUIBinder> getPropertyList(ParentBinder parentBinder, Object object, View parentView) {
        List<AbsUIBinder> properties = new ArrayList<>();
        if (object != null && parentView != null) {
            properties.addAll(getFieldPropertyList(parentBinder, object, parentView));
            properties.addAll(getMethodPropertyList(parentBinder, object, parentView));
        }
        return properties;
    }

    private static List<AbsUIBinder> getFieldPropertyList(ParentBinder parentBinder, Object object, View parentView) {
        List<AbsUIBinder> fieldList = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            ArrayList<View> views = getViewsByTag((ViewGroup) parentView, field.getName());
            List<AbsUIBinder> properties = new ArrayList<>();
            for (View view : views) {
                AbsUIBinder absUIBinder = getFieldViewWrapper(field, view, object);
                updateDataCallbackByParent(parentBinder, properties, absUIBinder);
            }
            AbsUIBinder absUIBinder = getAbsUIBinderFromProperties(parentBinder, object, properties);
            if (absUIBinder != null)
                fieldList.add(absUIBinder);
        }
        return fieldList;
    }

    private static AbsUIBinder getFieldViewWrapper(Field objectField, View fieldView, Object o) {
        AbsUIBinder absUIBinder = null;
        com.noisyz.bindlibrary.annotations.field.simple.Field
                simpleFieldType = objectField.getAnnotation(com.noisyz.bindlibrary.annotations.field.simple.Field.class);
        if (simpleFieldType != null) {
            absUIBinder = WrapperViewFactory.getSimplePropertyViewWrapper(simpleFieldType, fieldView, o, objectField);
        }

        ImageField imageField = objectField.getAnnotation(ImageField.class);
        if (imageField != null && fieldView instanceof ImageView) {
            absUIBinder = WrapperViewFactory.getImagePropertyViewWrapper(imageField, (ImageView) fieldView, o, objectField);
        }

        CustomField customFieldType = objectField.getAnnotation(CustomField.class);
        if (customFieldType != null) {
            absUIBinder = WrapperViewFactory.getSimplePropertyViewWrapper(customFieldType, fieldView, o, objectField);
        }

        ObjectField objectFieldType = objectField.getAnnotation(ObjectField.class);
        if (objectFieldType != null) {
            absUIBinder = new ObjectViewBinder(ReflectionUtils.getVariableValue(objectField, o)).registerView(fieldView);
        }

        AdapterViewField adapterViewField = objectField.getAnnotation(AdapterViewField.class);
        if (adapterViewField != null) {
            absUIBinder = WrapperViewFactory.getSimpleAdapterViewWrapper(adapterViewField, fieldView, o, objectField);
        }
        return absUIBinder;
    }


    private static List<AbsUIBinder> getMethodPropertyList(ParentBinder parentBinder, Object object, View parentView) {
        List<AbsUIBinder> methodList = new ArrayList<>();
        for (Method method : object.getClass().getDeclaredMethods()) {
            methodList.add(getImageGetterMethodProperty(parentBinder, method, object, parentView));
            methodList.add(getSimpleGetterMethodProperty(parentBinder, method, object, parentView));
            methodList.add(getCustomGetterMethodProperty(parentBinder, method, object, parentView));
            methodList.add(getObjectGetterMethodProperty(parentBinder, method, object, parentView));
            methodList.add(getSimpleAdapterGetterMethodPropertyList(parentBinder, method, object, parentView));
        }
        return methodList;
    }


    private static AbsUIBinder getSimpleGetterMethodProperty(ParentBinder parentBinder, Method getter, Object object, View parentView) {
        AbsUIBinder absUIBinder = null;
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
                List<View> views = getViewsByTag((ViewGroup) parentView, propertyKey);
                List<AbsUIBinder> properties = new ArrayList<>();
                for (View view : views) {
                    PropertyViewWrapper viewWrapper = WrapperViewFactory.
                            getSimplePropertyViewWrapper(propertyKey, getterMethod, setterMethod, view, object, getter, setter);
                    updateDataCallbackByParent(parentBinder, properties, viewWrapper);
                }
                absUIBinder = getAbsUIBinderFromProperties(parentBinder, object, properties);
            }
        }
        return absUIBinder;
    }

    private static AbsUIBinder getSimpleAdapterGetterMethodPropertyList
            (ParentBinder parentBinder, Method getter, Object object, View parentView) {
        AbsUIBinder absUIBinder = null;
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
                List<View> views = getViewsByTag((ViewGroup) parentView, propertyKey);
                List<AbsUIBinder> properties = new ArrayList<>();
                for (View view : views) {
                    PropertyViewWrapper viewWrapper = WrapperViewFactory.
                            getSimpleAdapterViewWrapper(propertyKey, getterMethod, setterMethod,
                                    (AdapterView) view, object, getter, setter);
                    updateDataCallbackByParent(parentBinder, properties, viewWrapper);
                }
                absUIBinder = getAbsUIBinderFromProperties(parentBinder, object, properties);
            }
        }
        return absUIBinder;
    }

    private static AbsUIBinder getCustomGetterMethodProperty(ParentBinder parentBinder, Method getter, Object object, View parentView) {
        AbsUIBinder absUIBinder = null;
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
                List<View> views = getViewsByTag((ViewGroup) parentView, propertyKey);
                List<AbsUIBinder> properties = new ArrayList<>();
                for (View view : views) {
                    PropertyViewWrapper viewWrapper = WrapperViewFactory.
                            getSimplePropertyViewWrapper(propertyKey, getterMethod, setterMethod, view, object, getter, setter);
                    updateDataCallbackByParent(parentBinder, properties, viewWrapper);
                }
                absUIBinder = getAbsUIBinderFromProperties(parentBinder, object, properties);
            }
        }
        return absUIBinder;
    }

    private static AbsUIBinder getObjectGetterMethodProperty(ParentBinder parentBinder, Method getter, Object object, View parentView) {
        AbsUIBinder absUIBinder = null;
        ObjectGetterMethod getterMethod = getter.getAnnotation(ObjectGetterMethod.class);
        if (getterMethod != null) {
            String propertyKey = getterMethod.propertyKey();
            if (propertyKey.isEmpty()) {
                propertyKey = parentView.getContext().getString(getterMethod.propertyKeyResId());
            }
            if (!propertyKey.isEmpty()) {
                List<View> views = getViewsByTag((ViewGroup) parentView, propertyKey);
                List<AbsUIBinder> properties = new ArrayList<>();
                for (View view : views) {
                    absUIBinder = new ObjectViewBinder(ReflectionUtils.invokeGetterMethod(getter, object)).registerView(view);
                    updateDataCallbackByParent(parentBinder, properties, absUIBinder);
                }
                absUIBinder = getAbsUIBinderFromProperties(parentBinder, object, properties);
            }
        }
        return absUIBinder;
    }

    private static AbsUIBinder getImageGetterMethodProperty(ParentBinder parentBinder, Method method, Object object, View parentView) {
        AbsUIBinder absUIBinder = null;
        ImageGetterMethod imageGetterMethod = method.getAnnotation(ImageGetterMethod.class);
        if (imageGetterMethod != null) {
            String propertyKey = imageGetterMethod.propertyKey();
            if (propertyKey.isEmpty()) {
                propertyKey = parentView.getContext().getString(imageGetterMethod.propertyKeyResId());
            }
            if (!propertyKey.isEmpty()) {
                ArrayList<View> views = getViewsByTag((ViewGroup) parentView, propertyKey);
                List<AbsUIBinder> properties = new ArrayList<>();
                for (View view : views) {
                    if (view instanceof ImageView) {
                        PropertyViewWrapper viewWrapper = WrapperViewFactory.getImagePropertyViewWrapper(propertyKey,
                                imageGetterMethod, (ImageView) view, object, method);
                        if (viewWrapper != null) {
                            properties.add(viewWrapper);
                        }
                    }
                }
                absUIBinder = getAbsUIBinderFromProperties(parentBinder, object, properties);
            }
        }
        return absUIBinder;
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

    private static Method getAdapterSetterMethod(Object object, String propertyKey) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            SimpleAdapterViewSetter setterMethod = method.getAnnotation(SimpleAdapterViewSetter.class);
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

    private static void updateDataCallbackByParent(ParentBinder parentBinder, List<AbsUIBinder> properties, AbsUIBinder viewWrapper) {
        if (viewWrapper != null) {
            properties.add(viewWrapper);
            if (!viewWrapper.hasDataUpdatedCallback() && parentBinder.hasDataUpdatedCallback()) {
                viewWrapper.setDataUpdatedCallback(parentBinder.getDataUpdatedCallback());
            }
        }
    }

    private static AbsUIBinder getAbsUIBinderFromProperties(ParentBinder parentBinder, Object object, List<AbsUIBinder> properties) {
        AbsUIBinder absUIBinder = null;
        if (properties.size() > 1) {
            absUIBinder = new TreeUIBinder(object, parentBinder).addChildren(properties);
        } else if (!properties.isEmpty()) {
            absUIBinder = properties.get(0);
        }
        return absUIBinder;
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
                    for (String tagObj : tagsObj) {
                        boolean onlyOne = !tagObj.contains("#");
                        boolean isField = onlyOne ? tagObj.equals(tag) : tagObj.contains(tag);
                        if (!TextUtils.isEmpty(tagObj) && isField) {
                            views.add(child);
                            String newTag = "";
                            for(int j = 0; j<tagsObj.length;j++){
                                newTag+=tagObj;
                                if(j<tagsObj.length-1)
                                    newTag+="|";
                            }
                            child.setTag(newTag);
                        }
                    }
                }
            }
        }
        return views;
    }

}
