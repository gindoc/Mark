package com.cwenhui.mark.common;

/**
 * Created by cwenhui on 2016.02.23
 */
public class TextUtils {
    /**
     * 检测字符序列是否为空（去掉前后空格）
     * 注：不去掉前后空格的请使用系统自带的TextUtils.isEmpty（CharSequence sequence）
     * @param sequence
     * @return
     */
    public static boolean isEmpty(CharSequence sequence) {
        if (sequence == null || sequence.toString().trim().length() == 0) {
            return false;
        }
        return true;
    }
}
