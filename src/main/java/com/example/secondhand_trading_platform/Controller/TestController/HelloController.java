package com.example.secondhand_trading_platform.Controller.TestController;

import com.example.secondhand_trading_platform.POJO.Response;
import com.example.secondhand_trading_platform.POJO.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @PostMapping("/hello")
    public Response register(@RequestBody User user, @RequestParam String code) {
        user.print();
        System.out.println("\n\n\n\n\n");
        NewDir newDir = new NewDir();
        String URL = newDir.selectInitialPicture(user.getUserName());
        user.setPictureUrl(URL);
        user.print();
        return Response.success();
    }
}
