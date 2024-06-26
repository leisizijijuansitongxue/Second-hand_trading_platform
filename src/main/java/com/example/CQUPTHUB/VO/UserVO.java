package com.example.CQUPTHUB.VO;

import com.example.CQUPTHUB.Tools.CustomPasswordEncoder;
import lombok.Data;

@Data
public class UserVO {

    private String userName;
    private String userPassword;
    private String userNewPassword;
    private String userEmail;
    private String userPhone;
    private String userNickname;
    private String code;

    public String HashCodedUserPassword(String userPassword) {
        CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();
        return customPasswordEncoder.encode(userPassword);
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();
        return customPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
