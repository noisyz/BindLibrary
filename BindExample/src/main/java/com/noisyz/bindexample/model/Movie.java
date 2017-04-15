package com.noisyz.bindexample.model;

import com.noisyz.bindlibrary.annotation.methods.simple.Bind;

import java.util.Date;

@Bind
public class Movie extends SomeObject {

    private String genre, title;

    private String imageUrl;

    private boolean isVisible;

    private Date publicationDate;

    public Movie() {
    }

    public Movie setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int SomeInteger() {
        return 0;
    }

    public Integer someWrappedInteger() {
        return 0;
    }

    public long SomeLong() {
        return 0L;
    }

    public Long someWrappedLong() {
        return 0L;
    }

    public Movie setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public Movie setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public String getTitle() {
        return title;
    }


    public Movie setTitle(String title) {
        this.title = title;
        return this;
    }

    public Movie setVisible(boolean isVisible) {
        this.isVisible = isVisible;
        return this;
    }

    public boolean isVisible() {
        return isVisible;
    }

}
