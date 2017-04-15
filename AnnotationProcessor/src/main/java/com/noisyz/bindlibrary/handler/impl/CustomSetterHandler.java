package com.noisyz.bindlibrary.handler.impl;

import com.noisyz.bindlibrary.annotation.methods.simple.CustomSetter;
import com.noisyz.bindlibrary.handler.AnnotationHandler;
import com.noisyz.bindlibrary.models.DefaultMethodItem;
import com.noisyz.bindlibrary.models.key.Key;
import com.noisyz.bindlibrary.models.key.KeyManager;
import com.noisyz.bindlibrary.models.CustomMethodItem;
import com.noisyz.bindlibrary.models.base.MethodItem;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

/**
 * Created by nero232 on 13.04.17.
 */

public class CustomSetterHandler extends AnnotationHandler<CustomSetter> {
    @Override
    protected MethodItem processAnnotation(ExecutableElement element, CustomSetter customSetter) {
        List<? extends VariableElement> variableElements = element.getParameters();
        if (!variableElements.isEmpty() && variableElements.size() == 1) {
            VariableElement variableElement = variableElements.get(0);
            String variableType = variableElement.asType().toString();
            return new CustomMethodItem(customSetter.value(), element.getSimpleName().toString(), variableType);
        }
        return null;
    }

    @Override
    protected Class<CustomSetter> getAnnotation() {
        return CustomSetter.class;
    }

    @Override
    protected Mode getMode() {
        return Mode.SETTER;
    }

    @Override
    protected Key getKey(CustomSetter customSetter, Element element) {
        return KeyManager.getKey(customSetter, element);
    }
}
