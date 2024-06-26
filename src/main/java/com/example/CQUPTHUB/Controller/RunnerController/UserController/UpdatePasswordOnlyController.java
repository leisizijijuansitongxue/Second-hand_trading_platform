package com.example.CQUPTHUB.Controller.RunnerController.UserController;


import com.example.CQUPTHUB.POJO.Response;
import com.example.CQUPTHUB.POJO.User;
import com.example.CQUPTHUB.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设想该类为重置密码的控制类
 */

@RestController
public class UpdatePasswordOnlyController {

    @Autowired
    private UserServiceImpl userService;
    User user = new User();

    @PostMapping("/updatePasswordOnly")
    public Response updatePasswordOnly(@RequestParam("UserId")long Id
            , @RequestParam("newPassword")String newpassword) {
        user.setUserPassword(newpassword);
        newpassword = user.getUserPassword();
        try {
            userService.UpdatePasswordOnly(Id, newpassword);
            return Response.success();
        }catch (Exception e){
            return Response.error(500 , e.getMessage());
        }
    }
}
