package com.example.CQUPTHUB.Controller.RunnerController.UserController.UpdateController;


import com.example.CQUPTHUB.Exception.updateException;
import com.example.CQUPTHUB.POJO.Response;
import com.example.CQUPTHUB.Service.Impl.EmailService;
import com.example.CQUPTHUB.Service.Impl.UserServiceImpl;
import com.example.CQUPTHUB.Tools.CheckCorrectly;
import com.example.CQUPTHUB.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 该账号的作用为可以更新用户账号的大部分信息
 * UserID balance role 随系统进行更新创建的信息无法在这里更新
 * password需要对账号信息进行进一步确认后才能进行修改
 * 前端需要将所有更新的信息交予后端进行更新
 * 包括用户当前的密码
 *
 * 设想中的总体更新，接下来将实现单独信息更新对项目实现进行重构
 */

@RestController
@RequestMapping("/api/update")
public class BaseUpdateController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private EmailService emailService;


    @PostMapping("/updateNickname")
    public Response updateUserNickname(@RequestBody UserVO uservo) {
        try {

            userService.updateUserNickname(uservo.getUserNickname(), uservo.getUserName());
            return Response.success(200 , "success" , userService.findUserByUsername(uservo.getUserName()));

        }catch (updateException e){
            return Response.error(400 , e.getMessage());
        }catch (Exception e){
            return Response.error(500 , e.getMessage());
        }
    }

    @PostMapping("/updatePhonenumber")
    public Response UpdateUserPhoneNumber(@RequestBody UserVO uservo) {
        try {

            userService.updateUserPhoneNumber(uservo.getUserPhone() , uservo.getUserName());
            return Response.success(200 , "success" , userService.findUserByUsername(uservo.getUserName()));

        }catch (updateException e){
            return Response.error(400 , e.getMessage());
        }catch (Exception e){
            return Response.error(500 , e.getMessage());
        }
    }

    @PostMapping("/updatePasswordByOldPassword")
    public Response UpdateUserPassword(@RequestBody UserVO uservo) {

        try {

            userService.updateUserPasswordByOldPassword(uservo.getUserNewPassword() , uservo.getUserPassword(), uservo.getUserName());
            return Response.success(200 , "success" , userService.findUserByUsername(uservo.getUserName()));
        }catch (updateException e){
            return Response.error(400 , e.getMessage());
        }catch (Exception e){
            return Response.error(500 , e.getMessage());
        }
    }


    @PostMapping("/send-code")
    public Response sendVerificationCode(@RequestBody UserVO uservo) {
        try {
            if (CheckCorrectly.isValidEmail(uservo.getUserEmail())) {
                emailService.sendVerificationCode(uservo.getUserEmail());
                return Response.success();
            } else {
                return Response.error(400, "无效的邮箱地址");
            }
        } catch (Exception e) {
            return Response.error(500, "发送验证码失败: " + e.getMessage());
        }
    }


    @PostMapping("/udatePasswordByEmail")
    public Response UpdateUserPasswordByEmail(@RequestBody UserVO uservo) {

        try {
            if (emailService.verifyCode(uservo.getUserEmail(), uservo.getCode())){

                userService.updateUserPasswordByEmail(uservo.getUserNewPassword() , uservo.getUserEmail());
                return Response.success(200 , "success" , userService.findeUserByEmail(uservo.getUserEmail()));

            }else {
                return Response.error(400 , "验证码看错误");
            }
        }catch (updateException e){
            return Response.error(400 , e.getMessage());
        }catch (Exception e){
            return Response.error(500 , e.getMessage());
        }
    }

}

