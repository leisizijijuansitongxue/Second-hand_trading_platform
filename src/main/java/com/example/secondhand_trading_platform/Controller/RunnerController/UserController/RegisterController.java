package com.example.secondhand_trading_platform.Controller.RunnerController.UserController;

import com.example.secondhand_trading_platform.POJO.Response;
import com.example.secondhand_trading_platform.POJO.User;
import com.example.secondhand_trading_platform.Service.Impl.EmailService;
import com.example.secondhand_trading_platform.Service.Impl.UserServiceImpl;
import com.example.secondhand_trading_platform.Exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 实现用户注册的控制类
 */

@RestController
public class RegisterController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private EmailService emailService;


    @PostMapping("/api/register")
    public Response register(@RequestBody User user , @RequestParam String code) {
        try {
            // 验证验证码
            boolean isCodeValid = emailService.verifyCode(user.getEmail(), code);
            if (!isCodeValid) {
                return Response.error(400, "Invalid verification code");
            }
            // 注册用户信息
            userService.registerInformation(user);
            return Response.success();
        } catch (InvalidInputException e) {
            return Response.error(e.getStatusCode(), e.getMessage());
        } catch (DuplicateKeyException e) {
            return Response.error(400, e.getMessage());
        } catch (Exception e) {
            return Response.error(500, e.getMessage()/*"服务器内部错误"*/);
        }
    }

    public void VerificationCode(String Email) throws Exception {
        emailService.sendVerificationCode(Email);
    }
}
