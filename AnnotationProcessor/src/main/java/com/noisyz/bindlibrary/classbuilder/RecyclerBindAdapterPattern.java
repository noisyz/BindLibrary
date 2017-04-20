package com.noisyz.bindlibrary.classbuilder;

import com.noisyz.bindlibrary.StringUtils;
import com.noisyz.bindlibrary.classbuilder.base.Class;
import com.noisyz.bindlibrary.classbuilder.base.Constructor;
import com.noisyz.bindlibrary.classbuilder.base.Field;
import com.noisyz.bindlibrary.classbuilder.base.Method;

/**
 * Created by nero232 on 16.04.17.
 */

public class RecyclerBindAdapterPattern extends Class {

    public RecyclerBindAdapterPattern(String type) {
        addImport(type);
        addImport("com.noisyz.bindlibrary.base.impl.adapter.RecyclerBindAdapter");
        addImport("com.noisyz.bindlibrary.base.impl.ObjectViewBinder");
        addImport("com.noisyz.bindlibrary.callback.layoutcallback.LayoutResourceProvider");
        addImport("java.util.List");

        String shortType = StringUtils.getShortType(type);
        setup(shortType);
    }

    private void setup(String shortType) {
        addImport("com.bindlibrary.generated." + shortType + "ViewBinder");
        setPackageName("com.bindlibrary.generated." + shortType.toLowerCase() + ".adapter");
        setName(shortType + "BindAdapter");
        setSuperClass("RecyclerBindAdapter<" + shortType + ">");

        initConstructors(shortType);
        initMethods(shortType);
    }

    private void initConstructors(String type) {
        Constructor baseConstructor = buildEmptyConstructor(type);
        baseConstructor.addArgument(new Field().setTypeAndName("int", "layoutResID"))
                .addBody("super(itemList, layoutResID)");
        addConstructor(baseConstructor);

        Constructor multiTypeConstructor = buildEmptyConstructor(type);
        multiTypeConstructor.addArgument(new Field().setTypeAndName("LayoutResourceProvider<" + type + ">", "layoutProvider"))
                .addBody("super(itemList, layoutProvider)");
        addConstructor(multiTypeConstructor);
    }

    private void initMethods(String type) {
        Method method = new Method().setReturnType("ObjectViewBinder<" + type + ">")
                .setModifiers("protected")
                .setName("initObjectViewBinder")
                .addArgument(new Field().setTypeAndName(type, StringUtils.getTypeAsVariable(type)))
                .addBody("return new " + type + "ViewBinder(" + StringUtils.getTypeAsVariable(type) + ")");
        addMethod(method);
    }


    public Constructor buildEmptyConstructor(String type) {
        Constructor constructor = buildEmptyConstructor();
        constructor.addArgument(new Field().setTypeAndName("List<" + type + ">", "itemList"));
        return constructor;
    }
}
