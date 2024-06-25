package com.example.CQUPTHUB.Controller.RunnerController.UserController;


import com.example.CQUPTHUB.POJO.Response;
import com.example.CQUPTHUB.POJO.User;
import com.example.CQUPTHUB.Service.Impl.EmailService;
import com.example.CQUPTHUB.Service.Impl.UserServiceImpl;
import com.example.CQUPTHUB.Tools.CheckCorrectly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 实现用户登录的控制类
 */

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/login-with-password")
    public Response login(@RequestParam("identifier") String identifier, @RequestParam("password") String password) {
        try {
            // 判断 identifier 的类型
            //User loginedUser = null;
            User loginedUser = new User();
            if (CheckCorrectly.isValidPhoneNumber(identifier)) {
                loginedUser = userService.loginByPhoneNumber(identifier, password);
            } else if (CheckCorrectly.isValidEmail(identifier)) {
                loginedUser = userService.loginByEmail(identifier, password);
            } else {
                loginedUser = userService.loginByUserName(identifier, password);
            }

            if (loginedUser != null) {
                loginedUser.print();
                return Response.success(200, "操作成功", loginedUser);
            } else {
                return Response.error(400, "登录失败: 用户不存在或密码不匹配");
            }
        } catch (Exception e) {
            return Response.error(500, "登录失败: " + e.getMessage());
        }
    }

    @PostMapping("/send-code")
    public Response sendVerificationCode(@RequestParam("email") String email) {
        try {
            if (CheckCorrectly.isValidEmail(email)) {
                //System.out.println(email);
                emailService.sendVerificationCode(email);
                return Response.success();
            } else {
                return Response.error(400, "无效的邮箱地址");
            }
        } catch (Exception e) {
            return Response.error(500, "发送验证码失败: " + e.getMessage());
        }
    }

    @PostMapping("/login-with-code")
    public Response loginWithCode(@RequestParam("email") String email, @RequestParam("code") String code) {
        try {
            if (emailService.verifyCode(email, code)) {
                User loginedUser = userService.findeUserByEmail(email);
                if (loginedUser != null) {
                    return Response.success(200, "操作成功", loginedUser);
                } else {
                    return Response.error(400, "登录失败: 用户不存在");
                }
            } else {
                return Response.error(400, "验证码错误或已过期");
            }
        } catch (Exception e) {
            return Response.error(500, "登录失败: " + e.getMessage());
        }
    }

}
