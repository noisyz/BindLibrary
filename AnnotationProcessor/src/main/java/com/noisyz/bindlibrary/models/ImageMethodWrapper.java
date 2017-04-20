package com.noisyz.bindlibrary.models;

import com.noisyz.bindlibrary.StringUtils;
import com.noisyz.bindlibrary.models.base.MethodWrapper;

/**
 * Created by nero232 on 13.04.17.
 */

public class ImageMethodWrapper extends MethodWrapper {
    private String imageProvider;

    public ImageMethodWrapper(String imageProvider) {
        this.imageProvider = imageProvider;
    }

    @Override
    protected String getProperty(String valueProvider, String keyInString) {
        return "new ImageProperty(\"" + imageProvider + "\", new "
                + StringUtils.getShortType(valueProvider) + "(), " + keyInString + ")";
    }

    @Override
    public String getPropertyClassName() {
        return "com.noisyz.bindlibrary.property.ImageProperty";
    }
}
