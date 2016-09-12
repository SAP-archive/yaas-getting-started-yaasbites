package com.hybris.yaas.bites;

import static org.apache.commons.lang3.StringUtils.lowerCase;

/**
 * Created by d066419 on 04/07/16.
 */
public class StringUtils {
    private StringUtils() {
    }

    static String restrictThenLowercase(String string) {
        return lowerCase(restrictLength(string));
    }


    static String restrictLength(String string) {
        return string == null ? null : string.substring(0, Math.min(string.length(), 200));
    }
}
