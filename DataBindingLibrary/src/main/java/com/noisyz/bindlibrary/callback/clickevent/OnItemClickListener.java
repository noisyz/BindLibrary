package com.noisyz.bindlibrary.callback.clickevent;

import android.view.View;

/**
 * Created by oleg on 13.08.16.
 */
public interface OnItemClickListener<T> {
    void onItemClick(View convertView, int position, T t);
}
