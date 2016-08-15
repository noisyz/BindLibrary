package com.noisyz.databindinglibrary.wrappers.impl.methods;

import com.noisyz.databindinglibrary.wrappers.impl.view.AbsViewWrapper;

import java.lang.reflect.Method;

/**
 * Created by Oleg on 17.03.2016.
 */
public class GetterPropertyViewWrapper<V extends AbsViewWrapper> extends MethodPropertyViewWrapper<V> {

    public GetterPropertyViewWrapper(V v, Object object, Method method) {
        super(v, object, method, GETTER);
    }
}
