package com.noisyz.bindlibrary.callback.imageproperty.impl;

import com.bumptech.glide.Glide;
import com.noisyz.bindlibrary.callback.imageproperty.abs.AsyncImageProvider;

/**
 * Created by Oleg on 05.04.2016.
 */
public class ResourceImageProvider extends AsyncImageProvider<Integer> {

    @Override
    public void loadBitmap(Integer integer) {
        Glide.with(getView().getContext()).load(integer).dontAnimate().into(getView());
    }


}
