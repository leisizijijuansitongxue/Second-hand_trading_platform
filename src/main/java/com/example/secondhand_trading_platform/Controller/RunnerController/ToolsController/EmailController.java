package com.example.secondhand_trading_platform.Controller.RunnerController.ToolsController;


import com.example.secondhand_trading_platform.POJO.Response;
import com.example.secondhand_trading_platform.Service.Impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-verification-code")
    public Response sendVerificationCode(@RequestParam String email) {
        try {
            System.out.println(email);
            emailService.sendVerificationCode(email);
            return Response.success();
        } catch (Exception e) {
            return Response.error(500, "Error sending verification code: " + e.getMessage());
        }
    }

    @PostMapping("/verify-code")
    public Response verifyCode(@RequestParam String email, @RequestParam String code) {
        boolean isCodeValid = emailService.verifyCode(email, code);
        if (isCodeValid) {
            return Response.success();
        } else {
            return Response.error(400, "Invalid verification code");
        }
    }
}