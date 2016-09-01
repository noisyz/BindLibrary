package com.noisyz.bindlibrary.wrappers.impl.view.image;

import android.widget.ImageView;

import com.noisyz.bindlibrary.callback.imageproperty.ImageProvider;
import com.noisyz.bindlibrary.wrappers.impl.view.IViewBinder;

/**
 * Created by Oleg on 18.03.2016.
 */
public class ImageViewWrapper implements IViewBinder<Object,ImageView> {

    private ImageProvider<Object> imageProvider;

    public ImageViewWrapper(ImageProvider<Object> provider) {
        this.imageProvider = provider;
    }

    @Override
    public void bindUI(Object value, ImageView imageView) {
        if (imageProvider != null) {
            imageProvider.provideData(imageView, value);
        }
    }

}
