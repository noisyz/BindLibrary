package com.noisyz.bindlibrary.classbuilder;

import com.noisyz.bindlibrary.StringUtils;
import com.noisyz.bindlibrary.classbuilder.base.Class;
import com.noisyz.bindlibrary.classbuilder.base.Constructor;
import com.noisyz.bindlibrary.classbuilder.base.Field;
import com.noisyz.bindlibrary.classbuilder.base.Method;

/**
 * Created by nero232 on 15.04.17.
 */

public class ObjectBinderPattern extends Class {

    public ObjectBinderPattern setType(String type) {
        String packageName = type.substring(0, type.lastIndexOf("."));
        addImport(type);
        setPackageName(packageName);
        setup(type);
        return this;
    }

    private void setup(String type) {
        String name = StringUtils.getShortType(type);
        setName(name + "ViewBinder");
        String objectViewBinderClass = "com.noisyz.bindlibrary.base.impl.ObjectViewBinder";
        addImport(objectViewBinderClass);
        addImport("java.util.List");
        addImport("java.util.ArrayList");
        addImport("com.noisyz.bindlibrary.property.abs.Property");
        setSuperClass(StringUtils.getShortType(objectViewBinderClass) + "<" + name + ">");
        initConstructor(type);
        initFields();
        initMethods();
    }

    private void initConstructor(String type) {
        Constructor constructor = buildEmptyConstructor();
        Field field = new Field()
                .setTypeAndName(StringUtils.getShortType(type),
                        StringUtils.getTypeAsVariable(type));
        constructor.addArgument(field);
        constructor.addBody("super(" + StringUtils.getTypeAsVariable(type) + ")");
        addConstructor(constructor);
    }

    private void initFields() {
        Field field = new Field().setTypeAndName("List<Property>", "properties")
                .setInitializer("new ArrayList<>()")
                .setModifiers("private static final");
        addField(field);
    }

    private void initMethods() {
        Method method = new Method()
                .setModifiers("protected")
                .setReturnType("List<Property>")
                .setName("getProperties")
                .addBody("return properties");
        addMethod(method);
    }

    public ObjectBinderPattern addProperty(String property) {
        addStaticInitializerLine("properties.add(" + property + ")");
        return this;
    }

}
