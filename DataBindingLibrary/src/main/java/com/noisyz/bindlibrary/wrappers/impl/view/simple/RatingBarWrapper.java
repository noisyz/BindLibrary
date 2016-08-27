package com.noisyz.bindlibrary.wrappers.impl.view.simple;

import android.widget.RatingBar;

import com.noisyz.bindlibrary.wrappers.impl.view.AbsViewWrapper;

/**
 * Created by Oleg on 18.03.2016.
 */
public class RatingBarWrapper extends AbsViewWrapper<RatingBar> {
    public RatingBarWrapper(RatingBar ratingBar) {
        super(ratingBar);
    }

    @Override
    public void bindUI(Object object) {
        if (object != null) {
            getView().setRating((Float) object);
        }
    }
}
