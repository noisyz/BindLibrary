package com.noisyz.databindinglibrary.callback.imageproperty;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Oleg on 18.03.2016.
 */
public abstract class ImageProvider<T> implements BitmapLoader<T> {

    private View view;

    public void provideData(View view, T t) {
        this.view = view;
        loadBitmap(t);
    }

    protected View getView(){
        return view;
    }

    public void showBitmap(Bitmap bitmap) {
        if (view != null) {
            if (view instanceof ImageView) {
                ((ImageView) view).setImageBitmap(bitmap);
            } else {
                view.setBackgroundDrawable(new BitmapDrawable(bitmap));
            }
        }
    }

    public void release(){
        view = null;
    }
}
