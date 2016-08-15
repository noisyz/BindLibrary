package com.noisyz.databindinglibrary.callback.imageproperty.impl;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.noisyz.databindinglibrary.callback.imageproperty.abs.AsyncImageProvider;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by oleg on 27.04.16.
 */
public class PicassoImageProvider extends AsyncImageProvider<String> implements Target {

    private String imageUrl = "null";

    @Override
    public void loadBitmap(String s) {
        if (!imageUrl.equals(s)) {
            s = s.replaceAll("\\s", "%20");
            Picasso.with(getView().getContext()).load(s).into(this);
            imageUrl = s;
        }
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        Log.d("myLogs", "OnBitmap loaded");
        showBitmap(bitmap);
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
        Log.d("myLogs", "OnBitmap loaded failed");
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
        Log.d("myLogs", "OnBitmap loaded prepare");
    }
}
