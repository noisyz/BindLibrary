package com.noisyz.bindlibrary.base;

/**
 * Created by Oleg on 18.03.2016.
 */
public interface UIBinder<BO> {

    void bindUI();

    void setBindObject(BO bo);

    BO getBindObject();

    void release();

}
