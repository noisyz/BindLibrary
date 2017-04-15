package com.noisyz.bindlibrary.models.base;

/**
 * Created by nero232 on 22.03.17.
 */

public class MethodItem {

    private String name, valueType;

    public MethodItem(String name, String type) {
        this.name = name;
        this.valueType = type;
    }

    public String getType() {
        return valueType;
    }

    @Override
    public String toString() {
        return name;
    }

}
