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

    private String imageUrl = "null";

    @Override
    public void loadBitmap(String s) {
        if (!imageUrl.equals(s)) {
            String url = s.replaceAll("\\s", "%20");
            Glide.with(getView().getContext()).load(url).dontAnimate().into(new SimpleTarget<GlideDrawable>() {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    getView().setImageDrawable(resource);

                }
            });
            imageUrl = url;
        }
    }
}
