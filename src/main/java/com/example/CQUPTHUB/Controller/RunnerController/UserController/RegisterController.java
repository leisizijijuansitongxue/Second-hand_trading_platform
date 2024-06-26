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
        System.out.println("UserName: " + user.getUserName());

        NewDir newDir = new NewDir();
        String URL = newDir.selectInitialPicture(user.getUserName());
        user.setPictureUrl(URL);

        System.out.println("\n\n这里是完整信息测试\n");
        user.print();
        System.out.println("\n\n");

        try {
            // 验证验证码
            boolean isCodeValid = emailService.verifyCode(user.getUserEmail(), code);
            if (!isCodeValid) {
                return Response.error(400, "Invalid verification code");
            }

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
