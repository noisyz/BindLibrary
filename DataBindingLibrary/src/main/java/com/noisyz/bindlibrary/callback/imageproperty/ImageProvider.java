package com.noisyz.bindlibrary.callback.imageproperty;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by Oleg on 18.03.2016.
 */
public abstract class ImageProvider<T> implements BitmapLoader<T> {

    private WeakReference<ImageView> view;

    public void provideData(ImageView view, final T t) {
        this.view = new WeakReference<>(view);
        loadBitmap(t);
    }

    protected ImageView getView() {
        return view.get();
    }

    public void showBitmap(Bitmap bitmap) {
        if (view != null) {
            ImageView imageView = view.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    public void release() {
        view = null;
    }
}
