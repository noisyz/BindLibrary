package com.noisyz.databindinglibrary.callback.clickevent;

import android.view.View;

/**
 * Created by oleg on 13.08.16.
 */
public interface OnElementClickListener<O extends Object>{
    void onElementClick(View convertView, int elementId, int position, O o);
}
