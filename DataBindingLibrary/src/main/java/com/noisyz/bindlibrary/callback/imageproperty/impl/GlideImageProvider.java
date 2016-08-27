package com.noisyz.bindlibrary.callback.imageproperty.impl;

import com.bumptech.glide.Glide;
import com.noisyz.bindlibrary.callback.imageproperty.abs.AsyncImageProvider;

/**
 * Created by oleg on 27.04.16.
 */
public class GlideImageProvider extends AsyncImageProvider<String>{

    private String imageUrl = "null";

    @Override
    public void loadBitmap(final String s) {
        if (!imageUrl.equals(s)) {
            String url = s.replaceAll("\\s", "%20");
            Glide.with(getView().getContext()).load(url).crossFade().into(getView());
            imageUrl = url;
        }
    }
}
