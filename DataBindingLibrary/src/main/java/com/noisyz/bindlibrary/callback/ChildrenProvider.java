package com.noisyz.bindlibrary.callback;

import java.util.List;

/**
 * Created by oleg on 26.08.16.
 */
public interface ChildrenProvider<T> {
    List getChildren(T t);
}
