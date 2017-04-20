package com.noisyz.bindlibrary.classbuilder;

import com.noisyz.bindlibrary.StringUtils;
import com.noisyz.bindlibrary.classbuilder.base.Class;
import com.noisyz.bindlibrary.classbuilder.base.Field;
import com.noisyz.bindlibrary.classbuilder.base.Method;

/**
 * Created by nero232 on 16.04.17.
 */

public class ChildIViewBinderPattern extends Class {

    public ChildIViewBinderPattern(String type) {
        addImport("com.noisyz.bindlibrary.base.impl.ObjectViewBinder");
        addImport("com.noisyz.bindlibrary.wrappers.IViewBinder");
        addImport("com.noisyz.bindlibrary.wrappers.view.child.ChildViewBinder");
        addImport(type);
        setPackageName("com.bindlibrary.generated");
        setup(type);
    }

    private void setup(String type) {
        type = StringUtils.getShortType(type);
        setName(type + "IViewBinder");
        setSuperClass("ChildViewBinder<" + type + ">");

        Method method = new Method()
                .setName("getViewBinder")
                .setReturnType("ObjectViewBinder<" + type + ">")
                .setModifiers("protected")
                .addArgument(new Field().setTypeAndName(type, StringUtils.getTypeAsVariable(type)))
                .addBody("return new " + type + "ViewBinder(" + StringUtils.getTypeAsVariable(type) + ")");

        addMethod(method);
    }

}
