package com.example.CQUPTHUB.Controller.TestController;

import com.example.CQUPTHUB.POJO.Response;
import com.example.CQUPTHUB.POJO.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @PostMapping("/hello")
    public Response register(@RequestBody User user, @RequestParam String code) {
        user.print();
        System.out.println("\n\n\n\n\n");
        NewDir newDir = new NewDir();
        String URL = newDir.selectInitialPicture(user.getUser_name());
        user.setPicture_url(URL);
        user.print();
        return Response.success();

    }
}
