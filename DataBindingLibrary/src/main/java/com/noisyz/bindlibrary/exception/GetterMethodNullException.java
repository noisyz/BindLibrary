package com.noisyz.databindinglibrary.exception;

/**
 * Created by oleg on 15.08.16.
 */
public class GetterMethodNullException extends IllegalArgumentException {

    public GetterMethodNullException(){
        super("@GetterMethod annotation must be applied to your property method. It is necessary to bind view");
    }

}
