package com.noisyz.databindinglibrary.callback.clickevent;

import android.view.View;

/**
 * Created by oleg on 13.08.16.
 */
public interface OnItemClickListener<O extends Object> {
    void onItemClick(View convertView, int position, O o);
}
