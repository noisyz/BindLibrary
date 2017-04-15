package com.noisyz.bindlibrary.models.base;

import com.noisyz.bindlibrary.annotation.Type;

/**
 * Created by nero232 on 14.04.17.
 */

public class Primitive {

    private Type type;
    private String shortName, name;

    public Primitive(Type type, String name, String shortName) {
        this.type = type;
        this.name = name;
        this.shortName = shortName;
    }

    public Type getType() {
        return type;
    }

    public String getShortName() {
        return shortName;
    }

    public String getName() {
        return name;
    }
}
