package com.example.CQUPTHUB.Controller.RunnerController.UserController;
import com.example.CQUPTHUB.POJO.Response;
import com.example.CQUPTHUB.POJO.User;
import com.example.CQUPTHUB.Service.Impl.UserServiceImpl;
import com.example.CQUPTHUB.Tools.CheckCorrectly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 该控制类的作用在于确认用户信息，是用户能进行密码重置前的前置条件
 * 参考LoginController的实现方法
 */


@RestController
public class VerifyInformationController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("VerifyInformation")
    public Response VerifyInformation(@RequestParam("UserID") long UserID, @RequestParam("identifier") String identifier,
                          @RequestParam("password") String password) {

        try {
            // 判断 identifier 的类型
            User loginedUser = null;
            if (CheckCorrectly.isValidPhoneNumber(identifier)) {
                loginedUser = userService.loginByPhoneNumber(identifier, password);
            } else if (CheckCorrectly.isValidEmail(identifier)) {
                loginedUser = userService.loginByEmail(identifier, password);
            } else {
                loginedUser = userService.loginByUserName(identifier, password);
            }

            if (loginedUser != null && UserID == loginedUser.getUser_id()) {
                return Response.success(200, "操作成功", "");
            } else {
                return Response.error(500, "验证失败: 用户不存在或密码不匹配");
            }
        } catch (Exception e) {
            return Response.error(500, "登录失败: " + e.getMessage());
        }
    }

}
