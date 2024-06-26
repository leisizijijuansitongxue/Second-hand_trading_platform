///**
// * 废弃代码
// */
//
//
//
//package com.example.CQUPTHUB.Config;
//
//import com.example.CQUPTHUB.Tools.CustomPasswordEncoder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorize -> authorize
//                        .requestMatchers("/public/**").permitAll() // 修改为 antMatchers()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/login") // 登录页面
//                        .permitAll()
//                        .loginProcessingUrl("/login") // 登录处理 URL
//                )
//                .logout(logout -> logout
//                        .permitAll()
//                        .logoutSuccessUrl("/logout-success") // 登出成功后跳转的页面
//                );
//
//        return http.build();
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new CustomPasswordEncoder();
//    }
//}
