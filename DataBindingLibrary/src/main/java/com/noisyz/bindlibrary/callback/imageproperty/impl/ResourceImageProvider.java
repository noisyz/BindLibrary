package com.noisyz.bindlibrary.callback.imageproperty.impl;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.noisyz.bindlibrary.callback.imageproperty.abs.AsyncImageProvider;

/**
 * Created by Oleg on 05.04.2016.
 */
public class ResourceImageProvider extends AsyncImageProvider<Integer> {

    @Override
    public void loadBitmap(Integer integer) {
        Glide.with(getView().getContext()).load(integer).dontAnimate().into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                getView().setImageDrawable(resource);
            }
        });
    }


}
