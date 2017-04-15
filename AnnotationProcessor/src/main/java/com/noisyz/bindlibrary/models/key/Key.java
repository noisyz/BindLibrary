package com.noisyz.bindlibrary.models.key;

/**
 * Created by nero232 on 13.04.17.
 */

public class Key {
    private static final int DEFAULT_KEY_HASH_CODE = 0x13243546;
    private String keyInString;
    private int keyResourceId;

    public Key(String keyInString) {
        this.keyInString = keyInString;
    }

    public Key(int keyResourceId) {
        this.keyResourceId = keyResourceId;
    }

    public String getKeyInString() {
        return keyInString;
    }

    public int getKeyResourceId() {
        return keyResourceId;
    }

    @Override
    public int hashCode() {
        return DEFAULT_KEY_HASH_CODE;
    }

    @Override
    public boolean equals(Object o) {
        Key compared = (Key) o;
        boolean equals;
        if (keyInString == null || keyInString.length() == 0) {
            equals = keyResourceId == compared.keyResourceId;
        } else {
            equals = keyInString.equals(compared.keyInString);
        }
        return equals;
    }

    @Override
    public String toString() {
        return keyInString != null ? keyInString : String.valueOf(keyResourceId);
    }
}
