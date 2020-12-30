package com.debug.base.utils;

import android.text.TextUtils;

public class MappingUtils {
    public static final String GENDER_MALE = "male";
    public static final String GENDER_FEMALE = "female";

    public static String findGender(String gender) {
        if (TextUtils.isEmpty(gender)) {
            return "性别：未知";
        }
        if (GENDER_MALE.equals(gender)) {
            return "性别：男";
        }
        return "性别：女";
    }
}
