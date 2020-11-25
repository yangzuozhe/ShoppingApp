package com.example.shoppingapp.base;

public class StringUtils {
    public static boolean isEmploy(String info) {
        if (info.length() == 0 || info == null || info.equals("null")) {
            return true;
        } else {
            return false;
        }
    }
}
