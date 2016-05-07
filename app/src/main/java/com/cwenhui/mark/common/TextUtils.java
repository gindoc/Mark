package com.cwenhui.mark.common;

/**
 * Created by cwenhui on 2016.02.23
 */
public class TextUtils {
    public static boolean isEmpty(CharSequence sequence) {
        if (sequence == null || sequence.toString().trim().length() == 0) {
            return false;
        }
        return true;
    }
}
