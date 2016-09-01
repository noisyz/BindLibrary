package com.noisyz.bindlibrary.base.property;

import com.noisyz.bindlibrary.wrappers.PropertyViewWrapper;


/**
 * Created by Oleg on 05.04.2016.
 */
public class Property {

    private String propertyName;
    private PropertyViewWrapper propertyViewWrapper;

    public Property(String propertyName, PropertyViewWrapper propertyBinder){
        propertyBinder.setPropertyName(propertyName);
        this.propertyViewWrapper = propertyBinder;
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public PropertyViewWrapper getPropertyBinder() {
        return propertyViewWrapper;
    }

    public void release(){
        propertyName = null;
        propertyViewWrapper = null;
    }
}
