package com.example.shoppingapp.base;

public class StringUtils {
    public static boolean isEmploy(String info) {
        if (info == null || info.length() == 0 || info.equals("null")) {
            return true;
        } else {
            return false;
        }
    }
}
