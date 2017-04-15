package com.noisyz.bindlibrary.classbuilder.base;

import com.noisyz.bindlibrary.Environment;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.tools.JavaFileObject;

/**
 * Created by nero232 on 15.04.17.
 */

public class Class {

    private String name, packageName, superClass, modifiers;
    private List<String> imports, staticInitializer, implementedInterfaces;
    private List<Method> methods;
    private List<Field> fields;
    private List<Constructor> constructors;

    public Class() {
        imports = new ArrayList<>();
        staticInitializer = new ArrayList<>();
        implementedInterfaces = new ArrayList<>();
        methods = new ArrayList<>();
        fields = new ArrayList<>();
        constructors = new ArrayList<>();
        modifiers = "public";
    }

    public Class setName(String name) {
        this.name = name;
        return this;
    }

    public Class setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public Class setSuperClass(String superClass) {
        this.superClass = superClass;
        return this;
    }

    public Class setModifiers(String modifiers) {
        this.modifiers = modifiers;
        return this;
    }

    public Class addImport(String className) {
        this.imports.add(className);
        return this;
    }

    public Class addImplementation(String implementation) {
        this.implementedInterfaces.add(implementation);
        return this;
    }

    public Class addMethod(Method method) {
        this.methods.add(method);
        return this;
    }

    public Class addField(Field field) {
        this.fields.add(field);
        return this;
    }

    public Class addStaticInitializerLine(String line) {
        this.staticInitializer.add(line);
        return this;
    }

    public Class addConstructor(Constructor constructor) {
        this.constructors.add(constructor);
        return this;
    }

    public Constructor buildEmptyConstructor() {
        return new Constructor().setType(name);
    }

    @Override
    public String toString() {
        StringBuilder classBody = new StringBuilder();
        fillClassHeader(classBody);
        fillSignature(classBody);
        fillClassData(classBody);
        return classBody.toString();
    }

    private void fillClassHeader(StringBuilder classBody) {
        classBody.append("package ").append(packageName).append(";\n\n");

        for (String importClass : imports) {
            classBody.append("import ").append(importClass).append(";\n");
        }

        classBody.append("\n");
    }

    private void fillSignature(StringBuilder classBody) {
        classBody.append(modifiers).append(" class ").append(name);
        if (superClass != null)
            classBody.append(" ").append("extends ").append(superClass);

        if (!implementedInterfaces.isEmpty()) {
            classBody.append(" implements ");
            for (int index = 0; index < implementedInterfaces.size(); index++) {
                String implementation = implementedInterfaces.get(index);
                classBody.append(implementation);
                if (index < implementedInterfaces.size() - 1)
                    classBody.append(", ");
            }
        }
        classBody.append("{\n\n");
    }

    private void fillClassData(StringBuilder classBody) {
        if (!fields.isEmpty()) {
            for (Field field : fields)
                classBody.append("\t").append(field.toString()).append(";\n");
            classBody.append("\n");
        }

        if (!staticInitializer.isEmpty()) {
            classBody.append("\tstatic{\n");
            for (String line : staticInitializer)
                classBody.append("\t\t").append(line).append(";\n");
            classBody.append("\t}\n\n");
        }

        if (!constructors.isEmpty()) {
            for (Constructor constructor : constructors)
                classBody.append(constructor.toString()).append("\n\n");
        }

        for (Method method : methods) {
            classBody.append(method.toString()).append("\n\n");
        }
        classBody.append("}");
    }

    public String writeSource() {
        try {
            String sourceName = packageName + "." + name;
            JavaFileObject source = Environment.getInstance().getFiler().
                    createSourceFile(sourceName);
            Writer writer = source.openWriter();
            writer.write(toString());
            writer.flush();
            writer.close();
            return sourceName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
