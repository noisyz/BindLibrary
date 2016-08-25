package com.noisyz.databindinglibrary.wrappers.impl.view.image;

import android.view.View;

import com.noisyz.databindinglibrary.callback.imageproperty.ImageProvider;
import com.noisyz.databindinglibrary.wrappers.impl.view.AbsViewWrapper;

/**
 * Created by Oleg on 18.03.2016.
 */
public class ImageViewWrapper extends AbsViewWrapper<View> {

    private ImageProvider provider;

    public ImageViewWrapper(final ImageProvider provider, View imageView) {
        super(imageView);
        this.provider = provider;
        imageView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View view) {

            }

            @Override
            public void onViewDetachedFromWindow(View view) {
                ImageViewWrapper.this.provider.release();
                ImageViewWrapper.this.provider = null;
            }
        });
    }

    @Override
    public void bindUI(Object value) {
        if (value != null && provider != null) {
            provider.provideData(getView(), value);
        }
    }

}
