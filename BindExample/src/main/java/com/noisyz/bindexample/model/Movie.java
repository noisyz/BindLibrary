package com.noisyz.bindexample.model;

import com.noisyz.bindexample.utils.DateConverter;
import com.noisyz.bindexample.utils.DateToObject;
import com.noisyz.bindexample.utils.DateToUI;
import com.noisyz.bindlibrary.annotations.converters.Conversion;
import com.noisyz.bindlibrary.annotations.converters.ConvertToObject;
import com.noisyz.bindlibrary.annotations.converters.ConvertToUI;
import com.noisyz.bindlibrary.annotations.field.simple.Field;
import com.noisyz.bindlibrary.annotations.field.simple.ImageField;
import com.noisyz.bindlibrary.annotations.methods.simple.GetterMethod;
import com.noisyz.bindlibrary.annotations.methods.simple.SetterMethod;
import com.noisyz.bindlibrary.annotations.propertyType;
import com.noisyz.bindlibrary.callback.imageproperty.impl.GlideImageProvider;

import java.util.Date;

public class Movie{

    @Field(propertyType.TEXT)
    private String genre, title;

    @ImageField(GlideImageProvider.class)
    private String imageUrl;


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

    @GetterMethod(value = propertyType.TEXT, propertyKey = "publicationDate", convertToUI = @ConvertToUI(value = DateToUI.class))
    public Date getPublicationDate() {
        return publicationDate;
    }

    @SetterMethod(value = propertyType.TEXT, propertyKey = "publicationDate", convertToObject = @ConvertToObject(value = DateToObject.class))
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
