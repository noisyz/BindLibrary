package com.noisyz.bindlibrary.handler.impl;

import com.noisyz.bindlibrary.annotation.methods.simple.Image;
import com.noisyz.bindlibrary.handler.AnnotationHandler;
import com.noisyz.bindlibrary.models.ImageMethodWrapper;
import com.noisyz.bindlibrary.models.base.MethodItem;
import com.noisyz.bindlibrary.models.key.Key;
import com.noisyz.bindlibrary.models.key.KeyManager;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.MirroredTypeException;

/**
 * Created by nero232 on 13.04.17.
 */

public class ImageHandler extends AnnotationHandler<ImageMethodWrapper, Image> {

    @Override
    protected ImageMethodWrapper createMethodWrapper(ExecutableElement executableElement, Image image) {
        try {
            image.value();
        } catch (MirroredTypeException e) {
            return new ImageMethodWrapper(e.getTypeMirror().toString());
        }
        return null;
    }

    @Override
    protected MethodItem processAnnotation(ExecutableElement element, Image image) {
        String returnType = element.getReturnType().toString();
        return new MethodItem(element.getSimpleName().toString(), returnType);
    }

    @Override
    protected Class<Image> getAnnotation() {
        return Image.class;
    }

    @Override
    protected Mode getMode() {
        return Mode.GETTER;
    }

    @Override
    protected Key getKey(Image image, Element element) {
        return KeyManager.getKey(image, element);
    }
}
