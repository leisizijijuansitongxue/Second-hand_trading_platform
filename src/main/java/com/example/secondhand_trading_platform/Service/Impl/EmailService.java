package com.example.secondhand_trading_platform.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // 存储验证码和邮箱的映射
    private final Map<String, String> verificationCodes = new HashMap<>();

    public String sendVerificationCode(String toEmail) throws Exception {
        try {
            String code = generateVerificationCode();
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("161766870@qq.com");
            message.setTo(toEmail);
            message.setSubject("Verification Code");
            message.setText("Your verification code is: " + code);
            mailSender.send(message);
            verificationCodes.put(toEmail, code);
            return code;
        } catch (Exception e) {
            throw new Exception("Failed to send verification code: " + e.getMessage(), e);
        }
    }

    public boolean verifyCode(String email, String code) {
        String storedCode = verificationCodes.get(email);
        return storedCode != null && storedCode.equals(code);
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    public Object getVerificationCode() {
        return null;
    }
}
