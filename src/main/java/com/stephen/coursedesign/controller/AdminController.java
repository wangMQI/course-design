package com.stephen.coursedesign.controller;

import com.stephen.coursedesign.entity.Admin;
import com.stephen.coursedesign.entity.User;
import com.stephen.coursedesign.service.AdminService;
import com.stephen.coursedesign.util.annotation.VerifyToken;
import com.stephen.coursedesign.util.result.Code;
import com.stephen.coursedesign.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/18 12:05
 * @Version:
 * @Description:
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;


    @VerifyToken
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public Result login(@RequestParam String adminId,@RequestParam String password){
        return adminService.login(adminId,password);
    }


    @VerifyToken
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/changeUserStatus", produces = "application/json;charset=UTF-8")
    public boolean changeUserStatus(@RequestParam String userPhone,@RequestParam String status){
        return true;
    }

    @VerifyToken
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/feeezeUser", produces = "application/json;charset=UTF-8")
    public Result feeezeUser(@RequestParam String uid){
        return adminService.feeezeUser(uid)?new Result(Code.Success):new Result(Code.Fail);
    }


    @VerifyToken
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/deleteUser", produces = "application/json;charset=UTF-8")
    public Result deleteUser(@RequestParam String uid){
        return adminService.deleteUser(uid)?new Result(Code.Success):new Result(Code.Fail);
    }


    @VerifyToken
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/searchUser", produces = "application/json;charset=UTF-8")
    public Result searchUser(@RequestParam String userInfo){
        User u = adminService.searchUser(userInfo);
        return u != null ? new Result(Code.Success,u) : new Result(Code.Fail);
    }

    @VerifyToken
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/searchVideo", produces = "application/json;charset=UTF-8")
    public boolean searchVideo(@RequestParam String videoInfo){
        return true;
    }

    @VerifyToken
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/deleteVideo", produces = "application/json;charset=UTF-8")
    public boolean deleteVideo(@RequestParam String videoId){
        return true;
    }

    @VerifyToken
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/deleteComment", produces = "application/json;charset=UTF-8")
    public Result deleteComment(@RequestParam String commentId){
        return adminService.deleteComment(commentId) ? new Result(Code.Success):new Result(Code.Fail);
    }

    @VerifyToken
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/addAdmin", produces = "application/json;charset=UTF-8")
    public Result addAdmin(Admin admin){
        return adminService.addAdmin(admin) ? new Result(Code.Success):new Result(Code.Fail);
    }

    @VerifyToken
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/deleteAdmin", produces = "application/json;charset=UTF-8")
    public Result deleteAdmin(@RequestParam String adminId){
        return adminService.deleteAdmin(adminId) ? new Result(Code.Success):new Result(Code.Fail);
    }



}
