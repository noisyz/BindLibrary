package com.noisyz.bindlibrary.wrappers.impl.view.simple;

import android.widget.RatingBar;

/**
 * Created by Oleg on 18.03.2016.
 */
public class ChangebleRatingBarWrapper extends RatingBarWrapper implements RatingBar.OnRatingBarChangeListener{
    public ChangebleRatingBarWrapper(RatingBar ratingBar) {
        super(ratingBar);
        ratingBar.setOnRatingBarChangeListener(this);
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        bindObject(rating);
    }
}
