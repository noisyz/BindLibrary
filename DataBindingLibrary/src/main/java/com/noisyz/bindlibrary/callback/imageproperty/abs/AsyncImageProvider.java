package com.noisyz.databindinglibrary.callback.imageproperty.abs;

import com.noisyz.databindinglibrary.callback.imageproperty.ImageProvider;

/**
 * Created by Oleg on 18.03.2016.
 */
public abstract class AsyncImageProvider<T extends Object> extends ImageProvider<T> {

    public abstract void loadBitmap(T t);

}
