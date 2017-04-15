package com.noisyz.bindlibrary.wrappers.view.simple;

import android.widget.RatingBar;

import com.noisyz.bindlibrary.wrappers.ViewBinder;

/**
 * Created by Oleg on 18.03.2016.
 */
public class ChangeableRatingBarWrapper extends ViewBinder<Float, RatingBar> implements RatingBar.OnRatingBarChangeListener {

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        bindObject(rating);
    }

    @Override
    public void addListeners(RatingBar ratingBar) {
        ratingBar.setOnRatingBarChangeListener(this);
    }

    @Override
    public void removeListeners(RatingBar ratingBar) {
        ratingBar.setOnRatingBarChangeListener(null);
    }

    @Override
    public Float getViewValue(RatingBar ratingBar) {
        return ratingBar.getRating();
    }

    @Override
    public void bindUI(Float aFloat, RatingBar ratingBar) {
        ratingBar.setRating(aFloat);
    }
}
