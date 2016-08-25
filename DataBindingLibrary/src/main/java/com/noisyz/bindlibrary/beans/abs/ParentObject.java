package com.noisyz.databindinglibrary.beans.abs;

import com.noisyz.databindinglibrary.beans.BindObject;

import java.util.List;

/**
 * Created by oleg on 15.08.16.
 */
public interface ParentObject<T extends BindObject> {

    List<T> getChilds();

}
