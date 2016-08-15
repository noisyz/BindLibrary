package com.noisyz.databindinglibrary.callback.layoutcallback;

import com.noisyz.databindinglibrary.beans.abs.MultiTypeObject;

/**
 * Created by oleg on 13.08.16.
 */
public interface LayoutResourceProvider<T extends MultiTypeObject> {

    int getLayoutResourceID(int type);

}
