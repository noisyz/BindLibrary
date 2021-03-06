package com.noisyz.bindlibrary.callback.imageproperty.impl;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.noisyz.bindlibrary.callback.imageproperty.abs.AsyncImageProvider;


/**
 * Created by oleg on 27.04.16.
 */
public class GlideImageProvider extends AsyncImageProvider<String> {

    @Override
    public void loadBitmap(String s) {
        String url = s.replaceAll("\\s", "%20");
        if (getView() != null)
            Glide.with(getView().getContext()).load(url).dontAnimate().into(new SimpleTarget<GlideDrawable>() {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    if (getView() != null)
                        getView().setImageDrawable(resource);
                }
            });
    }
}

