package com.noisyz.bindlibrary.callback.clickevent;

import android.view.View;

/**
 * Created by oleg on 13.08.16.
 */
public interface OnElementClickListener<T>{
    void onElementClick(View convertView, int elementId, int position, T t);
}
