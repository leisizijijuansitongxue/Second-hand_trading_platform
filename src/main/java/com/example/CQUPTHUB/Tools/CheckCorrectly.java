package com.example.CQUPTHUB.Tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckCorrectly {
    // 验证邮箱格式的方法
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // 验证电话号码格式的方法
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneNumberRegex = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";
        Pattern pattern = Pattern.compile(phoneNumberRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

}
