package com.noisyz.bindlibrary;

/**
 * Created by nero232 on 15.04.17.
 */

public class StringUtils {

    public static String makeFirstCharLowerCase(String string) {
        return Character.toLowerCase(string.charAt(0)) + string.substring(1);
    }

    public static String makeFirstCharUpperCase(String string) {
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    public static String validateStringKey(String key) {
        return key.replaceAll("\\s+", "");
    }

    public static String getShortType(String type) {
        if (type.contains("."))
            type = type.substring(type.lastIndexOf(".") + 1);
        return type;
    }

    public static String getTypeAsVariable(String type) {
        return "m" + getShortType(type);
    }
}
