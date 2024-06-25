package com.example.CQUPTHUB.Controller.RunnerController.ToolsController;


import com.example.CQUPTHUB.Exception.RegisterException;
import com.example.CQUPTHUB.POJO.Response;
import com.example.CQUPTHUB.Service.Impl.EmailService;
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
            emailService.sendVerificationCode(email);
            return Response.success();
        } catch (RegisterException e) {
            return Response.error(400,  e.getMessage());
        } catch (Exception e) {
        return Response.error(500, e.getMessage());
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
