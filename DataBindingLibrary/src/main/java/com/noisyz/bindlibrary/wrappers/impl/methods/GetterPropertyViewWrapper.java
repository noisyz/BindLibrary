package com.noisyz.bindlibrary.wrappers.impl.methods;

import com.noisyz.bindlibrary.wrappers.impl.view.AbsViewWrapper;

import java.lang.reflect.Method;

/**
 * Created by Oleg on 17.03.2016.
 */
public class GetterPropertyViewWrapper<VW extends AbsViewWrapper> extends MethodPropertyViewWrapper<VW> {

    public GetterPropertyViewWrapper(VW vw, Object object, Method method) {
        super(vw, object, method, GETTER);
    }
}
