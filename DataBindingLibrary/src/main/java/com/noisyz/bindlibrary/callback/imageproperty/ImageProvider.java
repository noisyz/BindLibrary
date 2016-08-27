package com.noisyz.bindlibrary.callback.imageproperty;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Oleg on 18.03.2016.
 */
public abstract class ImageProvider<T> implements BitmapLoader<T> {

    private ImageView view;

    public void provideData(ImageView view, T t) {
        this.view = view;
        loadBitmap(t);
    }

    protected ImageView getView(){
        return view;
    }

    public void showBitmap(Bitmap bitmap) {
        if (view != null) {
            view.setImageBitmap(bitmap);
        }
    }

    public void release(){
        view = null;
    }
}
