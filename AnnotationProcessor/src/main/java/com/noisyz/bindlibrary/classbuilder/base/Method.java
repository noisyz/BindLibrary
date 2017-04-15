package com.noisyz.bindlibrary.classbuilder.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nero232 on 15.04.17.
 */

public class Method {

    private String returnType, modifiers, name;
    private List<String> bodyLines;
    private List<Field> args;

    public Method() {
        modifiers = "public";
        bodyLines = new ArrayList<>();
        args = new ArrayList<>();
        returnType = "void";
    }

    public Method setName(String name) {
        this.name = name;
        return this;
    }

    public Method setReturnType(String returnType) {
        this.returnType = returnType;
        return this;
    }

    public Method setModifiers(String modifiers) {
        this.modifiers = modifiers;
        return this;
    }

    public Method addArgument(Field field) {
        args.add(field);
        return this;
    }

    public Method addBody(String bodyPart) {
        bodyLines.add(bodyPart);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder method = new StringBuilder();
        fillSignature(method);
        fillMethodBody(method);
        return method.toString();
    }

    private void fillSignature(StringBuilder method){
        method.append("\t").append(modifiers);
        if (returnType != null)
            method.append(" ").append(returnType);
        if (name != null)
            method.append(" ").append(name);
        method.append("(");
        fillVariables(method);
        method.append("){\n");
    }

    private void fillVariables(StringBuilder method){
        for (int index = 0; index < args.size(); index++) {
            Field argument = args.get(index);
            method.append(argument.toString());
            if (index < args.size() - 1)
                method.append(", ");
        }
    }

    private void fillMethodBody(StringBuilder method){
        for (String line : bodyLines) {
            method.append("\t\t").append(line).append(";\n");
        }
        method.append("\t}");
    }
}
