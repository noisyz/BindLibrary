package com.noisyz.bindlibrary.classbuilder;

import com.noisyz.bindlibrary.StringUtils;
import com.noisyz.bindlibrary.classbuilder.base.Class;
import com.noisyz.bindlibrary.classbuilder.base.Field;
import com.noisyz.bindlibrary.classbuilder.base.Method;
import com.noisyz.bindlibrary.models.base.MethodWrapper;

/**
 * Created by nero232 on 15.04.17.
 */

public class ValueProviderPattern extends Class {

    private String parentClass, propertyKey;
    private MethodWrapper methodWrapper;

    public ValueProviderPattern(String parentClass, String propertyKey, MethodWrapper methodWrapper) {
        this.parentClass = parentClass;
        this.propertyKey = propertyKey;
        this.methodWrapper = methodWrapper;
        addImport(parentClass);
        addImport(methodWrapper.getType());
        addImport("com.noisyz.bindlibrary.wrappers.ValueProvider");
        setup();
    }

    private void setup() {
        String bindObjectName = StringUtils.getShortType(parentClass);

        String valueProviderPackage = "com.bindlibrary.generated." + bindObjectName.toLowerCase() +
                ".valueprovider";
        setPackageName(valueProviderPackage);
        String valueProviderClassName = bindObjectName +
                StringUtils.makeFirstCharUpperCase(propertyKey) + "ValueProvider";
        setName(valueProviderClassName);

        addImplementation("ValueProvider<" + bindObjectName + ", " + getType() + ">");

        addMethod(getGetter(bindObjectName));
        addMethod(getSetter(bindObjectName));
    }

    private String getType() {
        return StringUtils.getShortType(methodWrapper.getType());
    }

    private String getParentVariable() {
        return StringUtils.getTypeAsVariable(parentClass);
    }

    private Method getGetter(String parentName) {
        return new Method().setName("invokeGetter")
                .addArgument(new Field().setTypeAndName(parentName, StringUtils.getTypeAsVariable(parentName)))
                .setReturnType(getType())
                .addBody(" return " + getParentVariable()
                        + "." + methodWrapper.getGetter().toString() + "()");
    }

    private Method getSetter(String parentName) {
        Method setter = new Method().setName("invokeSetter")
                .addArgument(new Field().setTypeAndName(parentName, StringUtils.getTypeAsVariable(parentName)))
                .addArgument(new Field().setTypeAndName(getType(), StringUtils.getTypeAsVariable(getType())));
        if (methodWrapper.getSetter() != null)
            setter.addBody(getParentVariable()
                    + "." + methodWrapper.getSetter().toString()
                    + "(" + StringUtils.getTypeAsVariable(getType()) + ")");
        return setter;
    }
}
