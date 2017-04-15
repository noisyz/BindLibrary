package com.noisyz.bindlibrary.handler;

import com.noisyz.bindlibrary.Environment;
import com.noisyz.bindlibrary.models.key.Key;
import com.noisyz.bindlibrary.models.base.ClassWrapper;
import com.noisyz.bindlibrary.models.base.MethodItem;
import com.noisyz.bindlibrary.models.base.MethodWrapper;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.tools.Diagnostic;

/**
 * Created by nero232 on 22.03.17.
 */

public abstract class AnnotationHandler<T extends Annotation> {

    public void process(ClassWrapper classWrapper, List<ExecutableElement> elements) {
        for (int index = 0; index < elements.size(); index++) {
            ExecutableElement element = elements.get(index);
            T t = element.getAnnotation(getAnnotation());
            if (t != null) {
                Key key = getKey(t, element);

                if (key != null) {
                    MethodWrapper methodWrapper = classWrapper.getMethodWrapper(key);
                    if (methodWrapper == null) {
                        methodWrapper = new MethodWrapper();
                    }

                    MethodItem methodItem = processAnnotation(element, t);
                    if (methodItem != null) {
                        switch (getMode()) {
                            case GETTER:
                                methodWrapper.setGetter(methodItem);
                                break;
                            case SETTER:
                                methodWrapper.setSetter(methodItem);
                                break;
                        }
                        elements.remove(index);
                        index--;
                        classWrapper.putMethodWrapper(key, methodWrapper);
                    }
                }
            }
        }
    }

    protected abstract MethodItem processAnnotation(ExecutableElement element, T t);

    protected abstract Class<T> getAnnotation();

    protected abstract Mode getMode();

    protected abstract Key getKey(T t, Element element);

    public enum Mode {
        GETTER, SETTER
    }
}
