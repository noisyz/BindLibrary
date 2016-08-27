package com.noisyz.bindlibrary.base.property;

import com.noisyz.bindlibrary.base.AbsUIBinder;
import com.noisyz.bindlibrary.wrappers.PropertyViewWrapper;

/**
 * Created by Oleg on 05.04.2016.
 */
public class Property {

    private String propertyName;
    private PropertyViewWrapper propertyBinder;

    public Property(String propertyName, PropertyViewWrapper propertyBinder){
        this.propertyBinder = propertyBinder;
        this.propertyName = propertyName;
        this.propertyBinder.setPropertyName(propertyName);
    }

    public String getPropertyName() {
        return propertyName;
    }

    public AbsUIBinder getPropertyBinder() {
        return propertyBinder;
    }

    public void release(){
        propertyBinder.release();
        propertyBinder = null;
        propertyName = null;
    }
}
