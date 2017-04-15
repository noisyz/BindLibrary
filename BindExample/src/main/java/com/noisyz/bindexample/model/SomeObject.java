package com.noisyz.bindexample.model;

import com.noisyz.bindlibrary.annotation.Type;
import com.noisyz.bindlibrary.annotation.methods.simple.Getter;
import com.noisyz.bindlibrary.annotation.methods.simple.Setter;
import com.noisyz.noisybindexample.R;

/**
 * Created by nero232 on 14.04.17.
 */

public class SomeObject {
    private boolean isEnabled;

    private String date;

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Getter(value = Type.TEXT, key = "some date")
    public String getDate() {
        return date;
    }

    @Setter(value = Type.TEXT, key = "some date")
    public void setDate(String date) {
        this.date = date;
    }
}
