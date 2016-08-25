package com.noisyz.databindinglibrary.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Oleg on 17.03.2016.
 */
public class ReflectionUtils {

    public static Object getClassInstance(Class<?> clazz, Object... params){
        Object object = null;
        try {
            Class<?>[] classArr = new Class<?>[params.length];
            for(int i = 0 ; i < params.length; i++)
            classArr[i] = params[i].getClass();
            Constructor<?> constructor = clazz.getConstructor(classArr);
            object = constructor.newInstance(params);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static void invokeSetterMethod(Method method, Object object, Object value){
        try {
            method.setAccessible(true);
            if (method.isAccessible()) {
                method.invoke(object, value);
            }

        } catch (SecurityException | IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Object invokeGetterMethod(Method method, Object object) {
        Object result = null;
        try {
            method.setAccessible(true);
            if (method.isAccessible()) {
                result = method.invoke(object);
            }

        } catch (SecurityException | IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Object getVariableValue(Field f, Object object) {
        Object result = null;
        try {
            f.setAccessible(true);
            if (f.isAccessible()) {
                result = f.get(object);
            }

        } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void setVariableValue(Field f, Object parentObject, Object value) {
        try {
            f.set(parentObject, value);
        } catch (IllegalAccessException | SecurityException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


}