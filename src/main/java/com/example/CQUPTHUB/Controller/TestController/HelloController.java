package com.example.CQUPTHUB.Controller.TestController;

import com.example.CQUPTHUB.DAO.UserMapper;
import com.example.CQUPTHUB.POJO.Response;
import com.example.CQUPTHUB.POJO.User;
import com.example.CQUPTHUB.Service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @Autowired
    UserMapper userMapper;

    @PostMapping("/hello")
    public Response Test(@RequestParam("testname") String testname){
        userMapper.FindUserByUsername(testname);
        return Response.success();
    }
}
