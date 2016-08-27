package com.noisyz.bindlibrary.wrappers.impl.view.image;

import android.widget.ImageView;

import com.noisyz.bindlibrary.callback.imageproperty.ImageProvider;
import com.noisyz.bindlibrary.wrappers.impl.view.AbsViewWrapper;

/**
 * Created by Oleg on 18.03.2016.
 */
public class ImageViewWrapper extends AbsViewWrapper<ImageView> {

    private ImageProvider provider;

    public ImageViewWrapper(final ImageProvider provider, ImageView imageView) {
        super(imageView);
        this.provider = provider;
    }

    @Override
    public void bindUI(Object value) {
        if (value != null && provider != null) {
            provider.provideData(getView(), value);
        }
    }

}
