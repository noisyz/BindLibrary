package com.noisyz.bindlibrary.handler.impl;

import com.noisyz.bindlibrary.annotation.methods.simple.Image;
import com.noisyz.bindlibrary.handler.AnnotationHandler;
import com.noisyz.bindlibrary.models.key.Key;
import com.noisyz.bindlibrary.models.key.KeyManager;
import com.noisyz.bindlibrary.models.ImageMethodItem;
import com.noisyz.bindlibrary.models.base.MethodItem;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

/**
 * Created by nero232 on 13.04.17.
 */

public class ImageHandler extends AnnotationHandler<Image> {

    @Override
    protected MethodItem processAnnotation(ExecutableElement element, Image image) {
        String returnType = element.getReturnType().toString();
        return new ImageMethodItem(image.value(), element.getSimpleName().toString(), returnType);
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
