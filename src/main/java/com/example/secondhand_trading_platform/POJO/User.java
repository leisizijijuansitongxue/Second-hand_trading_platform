package com.example.secondhand_trading_platform.POJO;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import com.example.secondhand_trading_platform.Tools.CustomPasswordEncoder;

import java.util.Date;
import java.util.UUID;

@Data
@Table(name = "users")
public class User {

    //String name = "用户昵称" + UUID.randomUUID();
    // Getter for ID
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, unique = true)
    private String nickName = "用户昵称" + UUID.randomUUID() ;

    @Column(nullable = false)
    private String passWord;

    @Column(name = "user_email" , nullable = false, unique = true)
    private String email;

    @Column(name = "user_phone", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "registration_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;



    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(nullable = false)
    private Role role = Role.STUDENT;

    @Column(nullable = false)
    private double balance = 0.0;

    // Constructors
    public User() {
        this.registrationDate = new Date();
        this.nickName = "用户昵称" + UUID.randomUUID();

    }


    public User(String userName, String passWord, String email, String phoneNumber ) {
        this.userName = userName;
        setPassWord(passWord); // 设置密码时进行哈希处理
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nickName = "用户昵称" + UUID.randomUUID();
    }

    public void print(){
        System.out.println("Printing user details:");
        System.out.println("User name: " + this.userName);
        System.out.println("Password: " + this.passWord);
        System.out.println("Email: " + this.email);
        System.out.println("Phone number: " + this.phoneNumber);
        System.out.println("Registration date: " + this.registrationDate);
        System.out.println("Picture URL: " + this.pictureUrl);
        System.out.println("Role: " + this.role);
        System.out.println("Balance: " + this.balance);
        System.out.println("Nickname: " + this.nickName);
    }



    public void setPassWord(String password) {
        CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();
        this.passWord = customPasswordEncoder.encode(password);
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();
        return customPasswordEncoder.matches(rawPassword, encodedPassword);
    }

}
