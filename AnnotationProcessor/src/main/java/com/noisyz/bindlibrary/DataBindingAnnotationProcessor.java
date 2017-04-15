package com.noisyz.bindlibrary;

import com.noisyz.bindlibrary.annotation.methods.simple.Bind;
import com.noisyz.bindlibrary.handler.BindClassesHandler;
import com.noisyz.bindlibrary.handler.impl.ChildHandler;
import com.noisyz.bindlibrary.handler.impl.CustomGetterHandler;
import com.noisyz.bindlibrary.handler.impl.CustomSetterHandler;
import com.noisyz.bindlibrary.handler.impl.GetterHandler;
import com.noisyz.bindlibrary.handler.impl.ImageHandler;
import com.noisyz.bindlibrary.handler.impl.SetterHandler;
import com.noisyz.bindlibrary.handler.impl.SpinnerGetterHandler;
import com.noisyz.bindlibrary.handler.impl.SpinnerSetterHandler;
import com.noisyz.bindlibrary.models.key.Key;
import com.noisyz.bindlibrary.models.base.ClassWrapper;
import com.noisyz.bindlibrary.models.base.MethodWrapper;

import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;


@SupportedAnnotationTypes("com.noisyz.bindlibrary.*")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class DataBindingAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Environment.init(processingEnv);

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Bind.class);

        Map<String, ClassWrapper> classMap = new BindClassesHandler(elements)
                .addHandler(new GetterHandler())
                .addHandler(new SetterHandler())
                .addHandler(new ImageHandler())
                .addHandler(new SpinnerGetterHandler())
                .addHandler(new SpinnerSetterHandler())
                .addHandler(new CustomGetterHandler())
                .addHandler(new CustomSetterHandler())
                .addHandler(new ChildHandler())
                .processAndBuild();

        new CodeWriter().writeCodeForData(classMap);
        return true;
    }

}