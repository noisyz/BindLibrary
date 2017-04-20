package com.noisyz.bindlibrary.property;

import com.noisyz.bindlibrary.callback.imageproperty.ImageProvider;
import com.noisyz.bindlibrary.models.key.Key;
import com.noisyz.bindlibrary.property.abs.Property;
import com.noisyz.bindlibrary.wrappers.PropertyViewWrapper;
import com.noisyz.bindlibrary.wrappers.ValueProvider;
import com.noisyz.bindlibrary.wrappers.view.image.ImageViewWrapper;

/**
 * Created by nero232 on 15.04.17.
 */

public class ImageProperty<BO, T> extends Property<BO, T> {

    private ImageProvider<T> imageProvider;

    public ImageProperty(String imageProvider, ValueProvider<BO, T> valueProvider, Key key) {
        super(valueProvider, key);
        try {
            this.imageProvider = (ImageProvider<T>) Class.forName(imageProvider).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PropertyViewWrapper buildPropertyViewWrapper(BO bo) {
        if (imageProvider != null)
            return new PropertyViewWrapper<>(new ImageViewWrapper<>(imageProvider), getValueProvider(), bo, getKey());
        else
            return null;
    }
}
