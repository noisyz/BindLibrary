package com.noisyz.bindlibrary.handler.impl;

import com.noisyz.bindlibrary.annotation.methods.simple.CustomSetter;
import com.noisyz.bindlibrary.handler.AnnotationHandler;
import com.noisyz.bindlibrary.models.CustomMethodWrapper;
import com.noisyz.bindlibrary.models.base.MethodItem;
import com.noisyz.bindlibrary.models.key.Key;
import com.noisyz.bindlibrary.models.key.KeyManager;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.MirroredTypeException;

/**
 * Created by nero232 on 13.04.17.
 */

public class CustomSetterHandler extends AnnotationHandler<CustomMethodWrapper, CustomSetter> {
    @Override
    protected CustomMethodWrapper createMethodWrapper(ExecutableElement executableElement, CustomSetter customSetter) {
        try {
            customSetter.value();
        } catch (MirroredTypeException e) {
            return new CustomMethodWrapper(e.getTypeMirror().toString());
        }
        return null;
    }

    @Override
    protected MethodItem processAnnotation(ExecutableElement element, CustomSetter customSetter) {
        List<? extends VariableElement> variableElements = element.getParameters();
        if (!variableElements.isEmpty() && variableElements.size() == 1) {
            VariableElement variableElement = variableElements.get(0);
            String variableType = variableElement.asType().toString();
            return new MethodItem(element.getSimpleName().toString(), variableType);
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
