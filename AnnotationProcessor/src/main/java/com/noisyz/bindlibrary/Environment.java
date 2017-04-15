package com.noisyz.bindlibrary;

import javax.annotation.processing.ProcessingEnvironment;

/**
 * Created by nero232 on 13.04.17.
 */

public class Environment {

    private static ProcessingEnvironment instance;

    public static void init(ProcessingEnvironment processingEnvironment) {
        instance = processingEnvironment;
    }

    public static ProcessingEnvironment getInstance() {
        return instance;
    }

    public static void release() {
        instance = null;
    }

}
