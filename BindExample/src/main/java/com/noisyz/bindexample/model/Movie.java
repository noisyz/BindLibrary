package com.noisyz.bindexample.model;

import com.noisyz.bindexample.utils.DateConverter;
import com.noisyz.bindlibrary.annotations.converters.Conversion;
import com.noisyz.bindlibrary.annotations.field.Field;
import com.noisyz.bindlibrary.annotations.field.ImageField;
import com.noisyz.bindlibrary.annotations.propertyType;
import com.noisyz.bindlibrary.callback.imageproperty.impl.GlideImageProvider;

import java.util.Date;

public class Movie{

    @Field(propertyType.TEXT)
    private String genre, title;

    @ImageField(GlideImageProvider.class)
    private String imageUrl;

    @Field(value = propertyType.TEXT, twoWayConverter = @Conversion(DateConverter.class))
    private Date publicationDate;

    public Movie() {
    }

    public String getGenre() {
        return genre;
    }

    public Movie setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
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
}
