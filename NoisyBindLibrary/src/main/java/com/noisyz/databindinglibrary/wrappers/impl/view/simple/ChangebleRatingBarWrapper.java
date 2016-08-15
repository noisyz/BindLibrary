package com.noisyz.databindinglibrary.wrappers.impl.view.simple;

import android.widget.RatingBar;

import com.noisyz.databindinglibrary.wrappers.ObjectBinder;

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
