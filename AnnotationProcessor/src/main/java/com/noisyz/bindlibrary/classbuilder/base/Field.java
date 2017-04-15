package com.noisyz.bindlibrary.classbuilder.base;

import com.noisyz.bindlibrary.StringUtils;

/**
 * Created by nero232 on 15.04.17.
 */

public class Field {
    private String type, name, modifiers, initializer;

    public Field setTypeAndName(String type, String name) {
        this.type = type;
        this.name = name;
        return this;
    }

    public Field setModifiers(String modifiers) {
        this.modifiers = modifiers;
        return this;
    }

    public Field setInitializer(String initializer) {
        this.initializer = initializer;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder field = new StringBuilder();
        if (modifiers != null)
            field.append(modifiers).append(" ");

        field.append(StringUtils.getShortType(type)).append(" ").append(name);
        if (initializer != null) {
            field.append(" = ").append(initializer);
        }
        return field.toString();
    }
}
