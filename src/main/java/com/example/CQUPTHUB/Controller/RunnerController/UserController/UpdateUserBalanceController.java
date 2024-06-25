package com.example.CQUPTHUB.Controller.RunnerController.UserController;


import com.example.CQUPTHUB.POJO.Response;
import com.example.CQUPTHUB.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/updateBalance")
public class UpdateUserBalanceController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/topup")
    public Response updateUserBalance(@RequestParam double count , @RequestParam Long userid) {

        try {
            userService.updateUserBalance(userid , count);
            return Response.success();
        }catch (Exception e){
            return Response.error(500 , e.getMessage());
        }
    }
}
