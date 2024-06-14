package com.example.secondhand_trading_platform.POJO;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import com.example.secondhand_trading_platform.Tools.CustomPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@Data
@Table(name = "users")
public class User {

    // Getter for ID
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "registration_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(nullable = false)
    private String role = "USER";

    @Column(nullable = false)
    private double balance = 0.0;

    // Constructors
    public User() {
        this.registrationDate = new Date();
    }

    public User(String username, String password, String email, String phoneNumber) {
        this();
        this.username = username;
        setPassword(password); // 设置密码时进行哈希处理
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    // Setter for Password (with hash)
    public void setPassword(String password) {
        CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();
        this.password = customPasswordEncoder.encode(password);
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();
        return customPasswordEncoder.matches(rawPassword, encodedPassword);
    }

}
