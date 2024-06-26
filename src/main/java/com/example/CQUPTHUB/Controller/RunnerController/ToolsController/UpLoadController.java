//package com.example.CQUPTHUB.Controller.RunnerController.ToolsController;
//
//
//import com.example.CQUPTHUB.POJO.Response;
//import com.example.CQUPTHUB.POJO.User;
//import com.example.CQUPTHUB.Service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//
//import java.io.IOException;
//
//
//@RestController
//public class UpLoadController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/uploadProfilePicture")
//    public Response uploadProfilePicture(@RequestParam("username") String username,
//                                                     @RequestParam("file") MultipartFile file) {
//        try {
//            User user = userService.uploadProfilePicture(username, file);
//            if (user != null) {
//                return Response.success();
//            } else {
//                return Response.error(404 , "找不到对应页面");
//            }
//        } catch (IOException e) {
//            return Response.error(500 , e.getMessage());
//        }
//    }
//}


