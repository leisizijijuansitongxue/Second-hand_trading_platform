package com.example.CQUPTHUB.Controller.RunnerController.UserController;

import com.example.CQUPTHUB.Tools.NewDir;
import com.example.CQUPTHUB.POJO.Response;
import com.example.CQUPTHUB.POJO.User;
import com.example.CQUPTHUB.Service.Impl.EmailService;
import com.example.CQUPTHUB.Service.Impl.UserServiceImpl;
import com.example.CQUPTHUB.Exception.InvalidInputException;
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

        System.out.println("User object received: " + user);
        System.out.println("UserName: " + user.getUser_name());

        NewDir newDir = new NewDir();
        String URL = newDir.selectInitialPicture(user.getUser_name());
        user.setPicture_url(URL);

        try {
            // 验证验证码
            boolean isCodeValid = emailService.verifyCode(user.getUser_email(), code);
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
            return Response.error(500, "这里错了1"/*"服务器内部错误"*/);
        }
    }

    public void VerificationCode(String Email) throws Exception {
        emailService.sendVerificationCode(Email);
    }
}
