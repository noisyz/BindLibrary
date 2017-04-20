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

    public ObjectBinderPattern(String type) {
        setPackageName("com.bindlibrary.generated");
        setup(type);
    }

    private void setup(String type) {
        String name = StringUtils.getShortType(type);
        setName(name + "ViewBinder");
        String objectViewBinderClass = "com.noisyz.bindlibrary.base.impl.ObjectViewBinder";
        addImport(objectViewBinderClass);
        addImport(type);
        addImport("java.util.List");
        addImport("java.util.ArrayList");
        addImport("com.noisyz.bindlibrary.property.abs.Property");
        addImport("com.noisyz.bindlibrary.models.key.Key");
        addImport("com.noisyz.bindlibrary.annotation.Type");
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
