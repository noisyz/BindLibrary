package com.noisyz.bindlibrary.models.key;

import com.noisyz.bindlibrary.StringUtils;
import com.noisyz.bindlibrary.annotation.methods.simple.Child;
import com.noisyz.bindlibrary.annotation.methods.simple.CustomGetter;
import com.noisyz.bindlibrary.annotation.methods.simple.CustomSetter;
import com.noisyz.bindlibrary.annotation.methods.simple.Getter;
import com.noisyz.bindlibrary.annotation.methods.simple.Image;
import com.noisyz.bindlibrary.annotation.methods.simple.Setter;
import com.noisyz.bindlibrary.annotation.methods.simple.SpinnerGetter;
import com.noisyz.bindlibrary.annotation.methods.simple.SpinnerSetter;

import javax.lang.model.element.Element;

/**
 * Created by nero232 on 13.04.17.
 */

public class KeyManager {

    private static final String DEFAULT_GETTER_PREFIX = "get",
            DEFAULT_SETTER_PREFIX = "set",
            BOOLEAN_GETTER_PREFIX = "is";

    public static Key getKey(Getter method, Element element) {
        return getKey(element, method.key(), method.keyId());
    }

    public static Key getKey(Setter method, Element element) {
        return getKey(element, method.key(), method.keyId());
    }

    public static Key getKey(Child method, Element element) {
        return getKey(element, method.key(), method.keyId());
    }

    public static Key getKey(SpinnerGetter method, Element element) {
        return getKey(element, method.key(), method.keyId());
    }

    public static Key getKey(SpinnerSetter method, Element element) {
        return getKey(element, method.key(), method.keyId());
    }

    public static Key getKey(Image method, Element element) {
        return getKey(element, method.key(), method.keyId());
    }

    public static Key getKey(CustomGetter method, Element element) {
        return getKey(element, method.key(), method.keyId());
    }

    public static Key getKey(CustomSetter method, Element element) {
        return getKey(element, method.key(), method.keyId());
    }

    private static Key getKey(Element element, String keyInString, int keyResourceId) {
        if (keyInString.length() != 0)
            return new Key(keyInString);
        else if (keyResourceId != 0)
            return new Key(keyResourceId);
        else return buildKeyFromElement(element);
    }

    public static Key buildKeyFromElement(Element element) {
        String elementName = element.getSimpleName().toString();
        if (elementName.startsWith(DEFAULT_GETTER_PREFIX)) {
            elementName = elementName.substring(DEFAULT_GETTER_PREFIX.length());
        } else if (elementName.startsWith(DEFAULT_SETTER_PREFIX)) {
            elementName = elementName.substring(DEFAULT_SETTER_PREFIX.length());
        } else if (elementName.startsWith(BOOLEAN_GETTER_PREFIX)) {
            elementName = elementName.substring(BOOLEAN_GETTER_PREFIX.length());
        } else return null;

        elementName = StringUtils.makeFirstCharLowerCase(elementName);
        return new Key(elementName);
    }
}
