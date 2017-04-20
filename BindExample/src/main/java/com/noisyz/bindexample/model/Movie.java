package com.noisyz.bindexample.model;

import android.util.Log;

import com.noisyz.bindlibrary.annotation.methods.simple.Bind;
import com.noisyz.bindlibrary.annotation.methods.simple.Child;
import com.noisyz.bindlibrary.annotation.methods.simple.Image;
import com.noisyz.bindlibrary.callback.imageproperty.impl.GlideImageProvider;
import com.noisyz.bindlibrary.callback.imageproperty.impl.PicassoImageProvider;

import java.io.Serializable;
import java.util.Date;

@Bind(generateAdapters = true)
public class Movie implements Serializable {

    private String genre, title;

    private String imageUrl, date;


    public Movie() {
    }

    public Movie setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    @Image(PicassoImageProvider.class)
    public String getImageUrl() {
        return imageUrl;
    }

    public Movie setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Movie setDate(String date) {
        this.date = date;
        return this;
    }

    public String getTitle() {
        return title;
    }


    public Movie setTitle(String title) {
        this.title = title;
        return this;
    }

}
