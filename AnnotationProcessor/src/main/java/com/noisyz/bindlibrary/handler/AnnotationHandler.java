package com.noisyz.bindlibrary.handler;

import com.noisyz.bindlibrary.models.base.ClassWrapper;
import com.noisyz.bindlibrary.models.base.MethodItem;
import com.noisyz.bindlibrary.models.base.MethodWrapper;
import com.noisyz.bindlibrary.models.key.Key;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

/**
 * Created by nero232 on 22.03.17.
 */

public abstract class AnnotationHandler<T extends MethodWrapper, A extends Annotation> {

    public void process(ClassWrapper classWrapper, List<ExecutableElement> elements) {
        for (int index = 0; index < elements.size(); index++) {
            ExecutableElement element = elements.get(index);
            A a = element.getAnnotation(getAnnotation());
            if (a != null) {
                Key key = getKey(a, element);

                if (key != null) {
                    MethodWrapper methodWrapper = classWrapper.getMethodWrapper(key);
                    if (methodWrapper == null) {
                        methodWrapper = createMethodWrapper(element, a);
                    }

                    if (methodWrapper != null) {
                        MethodItem methodItem = processAnnotation(element, a);
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
    }

    protected abstract T createMethodWrapper(ExecutableElement executableElement, A a);

    protected abstract MethodItem processAnnotation(ExecutableElement executableElement, A a);

    protected abstract Class<A> getAnnotation();

    protected abstract Mode getMode();

    protected abstract Key getKey(A A, Element element);

    public enum Mode {
        GETTER, SETTER
    }
}
