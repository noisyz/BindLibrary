package com.noisyz.bindlibrary.wrappers.impl.obj.view.simple;

import android.widget.RatingBar;

import com.noisyz.bindlibrary.wrappers.impl.obj.IViewBinder;

/**
 * Created by Oleg on 18.03.2016.
 */
public class RatingBarWrapper implements IViewBinder<Float, RatingBar>{

    @Override
    public void bindUI(Float aFloat, RatingBar ratingBar) {
        ratingBar.setRating(aFloat);
    }
}
