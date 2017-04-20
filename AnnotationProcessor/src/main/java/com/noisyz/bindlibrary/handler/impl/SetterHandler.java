package com.noisyz.bindlibrary.handler.impl;

import com.noisyz.bindlibrary.annotation.Type;
import com.noisyz.bindlibrary.annotation.methods.simple.Setter;
import com.noisyz.bindlibrary.handler.AnnotationHandler;
import com.noisyz.bindlibrary.handler.PrimitivesHandler;
import com.noisyz.bindlibrary.models.DefaultMethodWrapper;
import com.noisyz.bindlibrary.models.base.MethodItem;
import com.noisyz.bindlibrary.models.key.Key;
import com.noisyz.bindlibrary.models.key.KeyManager;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

/**
 * Created by nero232 on 23.03.17.
 */

public class SetterHandler extends AnnotationHandler<DefaultMethodWrapper, Setter> {

    @Override
    protected DefaultMethodWrapper createMethodWrapper(ExecutableElement executableElement, Setter setter) {
        return new DefaultMethodWrapper(setter.value());
    }

    @Override
    protected MethodItem processAnnotation(ExecutableElement element, Setter setter) {
        if (setter.value() == Type.DEFAULT) {
            return PrimitivesHandler.buildSetterMethodItem(element);
        } else {
            List<? extends VariableElement> variableElements = element.getParameters();
            if (!variableElements.isEmpty() && variableElements.size() == 1) {
                VariableElement variableElement = variableElements.get(0);
                String variableType = variableElement.asType().toString();
                return new MethodItem(element.getSimpleName().toString(), variableType);
            }
        }
        return null;
    }

    @Override
    protected Class<Setter> getAnnotation() {
        return Setter.class;
    }

    @Override
    protected Mode getMode() {
        return Mode.SETTER;
    }

    @Override
    protected Key getKey(Setter setter, Element element) {
        return KeyManager.getKey(setter, element);
    }
}
