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

    //String name = "用户昵称" + UUID.randomUUID();
    // Getter for ID
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(nullable = false, unique = true)
    private String user_name;

    @Column(nullable = false)
    private String user_password;

    @Column(name = "user_email" , nullable = false, unique = true)
    private String user_email;

    @Column(name = "user_phone", nullable = false, unique = true)
    private String user_phone;

    @Column(nullable = false)
    private Role user_role = Role.STUDENT;

    @Column(nullable = false, unique = true)
    private String nick_name = "用户昵称" + UUID.randomUUID() ;

    @Column(nullable = false)
    private BigDecimal user_balance;

    @Column(name = "registration_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registration_time;

    @Column(name = "picture_url")
    private String picture_url;

    @Column(name = "teacher_information_id", unique = true)
    private Integer teacher_information_id;

    // Constructors
    public User() {
        this.nick_name = "用户昵称" + UUID.randomUUID();
        this.registration_time = new Date();
    }


    public User(String userName, String passWord, String email, String phoneNumber ) {
        this.user_name = userName;
        setPassWord(passWord); // 设置密码时进行哈希处理
        this.user_email = email;
        this.user_phone = phoneNumber;
        this.nick_name = "用户昵称" + UUID.randomUUID();
        this.registration_time = new Date();
        this.user_role = Role.STUDENT;
        this.user_balance = BigDecimal.ZERO;
    }

    public void print(){
        System.out.println("Printing user details:");
        System.out.println("User name: " + this.user_name);
        System.out.println("Password: " + this.user_password);
        System.out.println("Email: " + this.user_email);
        System.out.println("Phone number: " + this.user_phone);
        System.out.println("Registration date: " + this.registration_time);
        System.out.println("Picture URL: " + this.picture_url);
        System.out.println("Role: " + this.user_role);
        System.out.println("Balance: " + this.user_balance);
        System.out.println("Nickname: " + this.nick_name);
        System.out.println("Teacher information id: " + this.teacher_information_id);
    }



    public void setPassWord(String password) {
        CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();
        this.user_password = customPasswordEncoder.encode(password);
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();
        return customPasswordEncoder.matches(rawPassword, encodedPassword);
    }

}
