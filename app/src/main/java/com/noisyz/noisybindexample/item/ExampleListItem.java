package com.noisyz.noisybindexample.item;

import com.noisyz.databindinglibrary.annotations.field.SimpleFieldType;
import com.noisyz.databindinglibrary.annotations.propertyType;
import com.noisyz.databindinglibrary.beans.BindObject;
import com.noisyz.databindinglibrary.beans.abs.MultiTypeObject;

/**
 * Created by oleg on 02.07.16.
 */
public class ExampleListItem extends BindObject implements MultiTypeObject {

    public static final int RANDOM_TYPE_1 = 0;
    public static final int RANDOM_TYPE_2 = 1;
    public static final int RANDOM_TYPE_3 = 2;
    private int type;

    @SimpleFieldType(propertyType.BOOLEAN)
    private boolean isChecked;
    @SimpleFieldType(propertyType.TEXT)
    private String title, subtitle;

    public ExampleListItem(String title, String subtitle, boolean isChecked, int type) {
        this.type = type;
        this.title = title;
        this.subtitle = subtitle;
        this.isChecked = isChecked;
    }

    public ExampleListItem(String title, String subtitle, boolean isChecked) {
        this.title = title;
        this.subtitle = subtitle;
        this.isChecked = isChecked;
    }

    @Override
    public int getType(){
        return type;
    }

    @Override
    public int getTypeCount() {
        return 3;
    }
}
