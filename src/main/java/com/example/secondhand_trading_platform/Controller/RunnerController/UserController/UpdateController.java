package com.example.secondhand_trading_platform.Controller.RunnerController.UserController;


import com.example.secondhand_trading_platform.POJO.Response;
import com.example.secondhand_trading_platform.POJO.User;
import com.example.secondhand_trading_platform.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
public class UpdateController {

    @Autowired
    private UserServiceImpl userService;


    @PostMapping("/updateUser")
    public Response updateUser(@RequestBody User user) {
        try {
            User newUser = null;
            newUser = userService.findUserByUsername(user.getUsername());
            user.setId(newUser.getId());

            if (newUser != null) {

                userService.UpdateUser(user);
                newUser = userService.findUserByUsername2(user);
                return Response.success(200, "操作成功", newUser);

            } else {
                return Response.error(500, "登录失败: 用户不存在或密码不匹配");
            }

        }catch (Exception e) {
                return Response.error(500, /*user.getUsername() + "and" + user.getPassword()*/ e.getMessage());
        }
    }
}
