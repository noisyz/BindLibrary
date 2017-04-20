package com.noisyz.bindlibrary.handler.impl;

import com.noisyz.bindlibrary.annotation.methods.simple.Child;
import com.noisyz.bindlibrary.classbuilder.ChildIViewBinderPattern;
import com.noisyz.bindlibrary.handler.AnnotationHandler;
import com.noisyz.bindlibrary.models.CustomMethodWrapper;
import com.noisyz.bindlibrary.models.base.MethodItem;
import com.noisyz.bindlibrary.models.key.Key;
import com.noisyz.bindlibrary.models.key.KeyManager;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

/**
 * Created by nero232 on 13.04.17.
 */

public class ChildHandler extends AnnotationHandler<CustomMethodWrapper, Child> {
    @Override
    protected CustomMethodWrapper createMethodWrapper(ExecutableElement executableElement, Child child) {
        String returnType = executableElement.getReturnType().toString();
        String iViewBinder = new ChildIViewBinderPattern(returnType).writeSource();
        return new CustomMethodWrapper(iViewBinder);
    }

    @Override
    protected MethodItem processAnnotation(ExecutableElement element, Child child) {
        String returnType = element.getReturnType().toString();
        return new MethodItem(element.getSimpleName().toString(), returnType);
    }

    @Override
    protected Class<Child> getAnnotation() {
        return Child.class;
    }

    @Override
    protected Mode getMode() {
        return Mode.GETTER;
    }

    @Override
    protected Key getKey(Child child, Element element) {
        return KeyManager.getKey(child, element);
    }
}
