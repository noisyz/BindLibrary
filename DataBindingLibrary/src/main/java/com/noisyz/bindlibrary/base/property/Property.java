package com.noisyz.databindinglibrary.bind.base.property;

import com.noisyz.databindinglibrary.bind.base.AbsUIBinder;

/**
 * Created by Oleg on 05.04.2016.
 */
public class Property {

    private String propertyName;
    private AbsUIBinder propertyBinder;

    public Property(String propertyName, AbsUIBinder propertyBinder){
        this.propertyBinder = propertyBinder;
        this.propertyName = propertyName;
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
