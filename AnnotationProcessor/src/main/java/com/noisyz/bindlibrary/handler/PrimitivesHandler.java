package com.noisyz.bindlibrary.handler;

import com.noisyz.bindlibrary.annotation.Type;
import com.noisyz.bindlibrary.models.DefaultMethodWrapper;
import com.noisyz.bindlibrary.models.base.ClassWrapper;
import com.noisyz.bindlibrary.models.base.MethodItem;
import com.noisyz.bindlibrary.models.base.MethodWrapper;
import com.noisyz.bindlibrary.models.base.Primitive;
import com.noisyz.bindlibrary.models.key.Key;
import com.noisyz.bindlibrary.models.key.KeyManager;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

/**
 * Created by nero232 on 14.04.17.
 */

public class PrimitivesHandler {

    private static final List<Primitive> primitives;

    static {
        primitives = new ArrayList<>();
        primitives.add(new Primitive(Type.BOOLEAN, "java.lang.Boolean", "boolean"));
        primitives.add(new Primitive(Type.TEXT, "java.lang.String", "String"));
        primitives.add(new Primitive(Type.FLOAT_TEXT, "java.lang.Float", "float"));
        primitives.add(new Primitive(Type.DOUBLE_TEXT, "java.lang.Double", "double"));
        primitives.add(new Primitive(Type.TEXT_RES, "java.lang.Integer", "int"));
    }

    private static Type getTypeOfPrimitiveMethod(String methodType) {
        for (Primitive primitive : primitives)
            if (methodType.equals(primitive.getName()) || methodType.equals(primitive.getShortName()))
                return primitive.getType();
        return null;
    }

    private static String getReturnTypeOfPrimitiveMethod(Type type) {
        for (Primitive primitive : primitives)
            if (type.equals(primitive.getType()))
                return primitive.getName();
        return null;
    }

    public void processPrimitives(ClassWrapper classWrapper, List<? extends ExecutableElement> elements) {
        for (int index = 0; index < elements.size(); index++) {
            ExecutableElement executableElement = elements.get(index);
            Key key = KeyManager.buildKeyFromElement(executableElement);
            if (key != null) {
                MethodWrapper methodWrapper = classWrapper.getMethodWrapper(key);
                Type type = getTypeOfElement(executableElement);
                if (type != null) {
                    if (methodWrapper == null) {
                        methodWrapper = new DefaultMethodWrapper(type);
                    }

                    boolean processSuccessfully = processGetter(executableElement, methodWrapper)
                            || processSetter(executableElement, methodWrapper);

                    if (processSuccessfully) {
                        elements.remove(index);
                        index--;
                        classWrapper.putMethodWrapper(key, methodWrapper);
                    }
                }
            }
        }
    }

    private static boolean processGetter(ExecutableElement executableElement, MethodWrapper methodWrapper) {
        MethodItem methodItem = buildGetterMethodItem(executableElement);
        boolean success = methodItem != null;
        if (success)
            methodWrapper.setGetter(methodItem);
        return success;
    }

    private static boolean processSetter(ExecutableElement executableElement, MethodWrapper methodWrapper) {
        MethodItem methodItem = buildSetterMethodItem(executableElement);
        boolean success = methodItem != null;
        if (success)
            methodWrapper.setSetter(methodItem);
        return success;
    }


    public static Type getTypeOfElement(ExecutableElement executableElement) {
        String returnType = executableElement.getReturnType().toString();
        Type type = getTypeOfPrimitiveMethod(returnType);
        if (type != null) {
            return type;
        } else {
            List<? extends VariableElement> variableElements = executableElement.getParameters();
            if (!variableElements.isEmpty() && variableElements.size() == 1) {
                VariableElement variableElement = variableElements.get(0);
                String variableType = variableElement.asType().toString();
                type = getTypeOfPrimitiveMethod(variableType);
                if (type != null) {
                    return type;
                }
            }
        }
        return null;
    }

    public static MethodItem buildGetterMethodItem(ExecutableElement executableElement) {
        String returnType = executableElement.getReturnType().toString();
        Type type = getTypeOfPrimitiveMethod(returnType);
        if (type != null) {
            returnType = getReturnTypeOfPrimitiveMethod(type);
            return new MethodItem(executableElement.getSimpleName().toString(), returnType);
        } else return null;
    }

    public static MethodItem buildSetterMethodItem(ExecutableElement executableElement) {
        List<? extends VariableElement> variableElements = executableElement.getParameters();
        if (!variableElements.isEmpty() && variableElements.size() == 1) {
            VariableElement variableElement = variableElements.get(0);
            String variableType = variableElement.asType().toString();
            Type type = getTypeOfPrimitiveMethod(variableType);
            if (type != null) {
                variableType = getReturnTypeOfPrimitiveMethod(type);
                return new MethodItem(executableElement.getSimpleName().toString(), variableType);
            }
        }
        return null;
    }
}
