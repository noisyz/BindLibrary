package com.noisyz.bindlibrary.handler;

import com.noisyz.bindlibrary.Environment;
import com.noisyz.bindlibrary.annotation.methods.simple.Bind;
import com.noisyz.bindlibrary.models.base.ClassWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;

/**
 * Created by nero232 on 23.03.17.
 */

public class BindClassesHandler {
    private final List<AnnotationHandler> handlers;
    private final Set<? extends Element> elements;

    public BindClassesHandler(Set<? extends Element> elements) {
        this.elements = elements;
        handlers = new ArrayList<>();
    }

    public BindClassesHandler addHandler(AnnotationHandler annotationHandler) {
        handlers.add(annotationHandler);
        return this;
    }

    public Map<String, ClassWrapper> processAndBuild() {
        Map<String, ClassWrapper> classWrapperMap = new HashMap<>();
        for (Element element : elements) {
            Bind bind = element.getAnnotation(Bind.class);
            TypeElement typeElement = (TypeElement) element;
            String name = Environment.getInstance().getElementUtils()
                    .getBinaryName(typeElement).toString();
            boolean generateAdapters = bind.generateAdapters();
            ClassWrapper classWrapper = new ClassWrapper(generateAdapters);
            do {
                List<? extends ExecutableElement> elements = getMethodsFromClass(typeElement);
                for (AnnotationHandler handler : handlers) {
                    handler.process(classWrapper, elements);
                }
                new PrimitivesHandler().processPrimitives(classWrapper, elements);
                TypeMirror typeMirror = typeElement.getSuperclass();
                element = Environment.getInstance().getTypeUtils().asElement(typeMirror);
                typeElement = (TypeElement) element;
            }
            while (!typeElement.toString().equals("java.lang.Object"));

            classWrapperMap.put(name, classWrapper);
        }
        return classWrapperMap;
    }

    private List<? extends ExecutableElement> getMethodsFromClass(TypeElement classElement) {
        List<? extends Element> elements = classElement.getEnclosedElements();
        return ElementFilter.methodsIn(elements);
    }
}
