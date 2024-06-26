package com.example.CQUPTHUB.POJO;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import com.example.CQUPTHUB.Tools.CustomPasswordEncoder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Table(name = "users")
public class User {

    // Getter for ID
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String userPassword;

    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "user_phone", nullable = false, unique = true)
    private String userPhone;

    @Column(nullable = false)
    private Role userRole = Role.STUDENT;

    @Column(nullable = false, unique = true)
    private String userNickname = "用户昵称" + UUID.randomUUID();

    @Column(nullable = false)
    private BigDecimal userBalance;

    @Column(name = "registration_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationTime;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "teacher_information_id", unique = true)
    private Integer teacherInformationId;

    @Column(name = "user_state", nullable = false)
    private State userState = State.NORMAL;

    // Constructors
    public User() {
        this.userNickname = "用户昵称" + UUID.randomUUID();
        this.registrationTime = new Date();
        this.userBalance = BigDecimal.ZERO;
    }

    public User(String userName, String userPassword, String userEmail, String userPhone) {
        System.out.println("这个方法被调用2");
        this.userName = userName;
        setUserPassword(userPassword); // 设置密码时进行哈希处理
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userNickname = "用户昵称" + UUID.randomUUID();
        this.registrationTime = new Date();
        this.userRole = Role.STUDENT;
        this.userBalance = BigDecimal.ZERO;
        this.userState = State.NORMAL;
    }

    public void print() {
        System.out.println("Printing user details:");
        System.out.println("User name: " + this.userName);
        System.out.println("Password: " + this.userPassword);
        System.out.println("Email: " + this.userEmail);
        System.out.println("Phone number: " + this.userPhone);
        System.out.println("Registration date: " + this.registrationTime);
        System.out.println("Picture URL: " + this.pictureUrl);
        System.out.println("Role: " + this.userRole);
        System.out.println("Balance: " + this.userBalance);
        System.out.println("Nickname: " + this.userNickname);
        System.out.println("Teacher information id: " + this.teacherInformationId);
        System.out.println("User state: " + this.userState);
    }

    public void setUserPassword(String userPassword) {
        System.out.println("这个方法被调用1");
        CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();
        this.userPassword = customPasswordEncoder.encode(userPassword);
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();
        return customPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
