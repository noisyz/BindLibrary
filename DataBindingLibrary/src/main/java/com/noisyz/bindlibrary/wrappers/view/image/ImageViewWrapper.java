package com.noisyz.bindlibrary.wrappers.view.image;

import android.util.Log;
import android.widget.ImageView;

import com.noisyz.bindlibrary.callback.imageproperty.ImageProvider;
import com.noisyz.bindlibrary.wrappers.IViewBinder;

/**
 * Created by Oleg on 18.03.2016.
 */
public class ImageViewWrapper<T> implements IViewBinder<T, ImageView> {

    private ImageProvider<T> imageProvider;

    public ImageViewWrapper(ImageProvider<T> provider) {
        this.imageProvider = provider;
    }

    @Override
    public void bindUI(T t, ImageView imageView) {
        if (imageProvider != null) {
            imageProvider.provideData(imageView, t);
        }
    }

}
