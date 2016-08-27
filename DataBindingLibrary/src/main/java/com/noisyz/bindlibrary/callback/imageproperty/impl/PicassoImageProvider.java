package com.noisyz.bindlibrary.callback.imageproperty.impl;

import com.noisyz.bindlibrary.callback.imageproperty.abs.AsyncImageProvider;
import com.squareup.picasso.Picasso;

/**
 * Created by oleg on 27.04.16.
 */
public class PicassoImageProvider extends AsyncImageProvider<String>{

    private String imageUrl = "null";

    @Override
    public void loadBitmap(final String s) {
        if (!imageUrl.equals(s)) {
            String url = s.replaceAll("\\s", "%20");
            Picasso.with(getView().getContext()).load(s).into(getView());
            imageUrl = url;
        }
    }
}
