package com.noisyz.bindlibrary.classbuilder.base;

import com.noisyz.bindlibrary.StringUtils;

/**
 * Created by nero232 on 15.04.17.
 */

public class Constructor extends Method {

    public Constructor setType(String type) {
        setReturnType(StringUtils.getShortType(type));
        setName(null);
        return this;
    }
}
