package com.jpamp.util;

import org.apache.commons.lang3.RandomStringUtils;

public class CustUtil {

    public static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static String randomString(final int count){
        return RandomStringUtils.random(count, CHARS);
    }

    public static String result (){
        return String.valueOf(System.currentTimeMillis());
    }
}
