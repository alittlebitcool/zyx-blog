//package com.zyx.web;
//
//import com.zyx.entity.User;
//import com.zyx.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created by YuXingZh on 19-3-19
// */
//@Controller
//public class AdminController {
//    @Autowired
//    UserService userService;
//
//    @ResponseBody
//    @PostMapping("/changePassword")
//    public String changePassword(@RequestParam("phone") String phone,
//                                 @RequestParam("authCode") String authCode,
//                                 @RequestParam("newPassword") String newPassword,
//                                 HttpServletRequest request){
//
//        String trueMsgCode = (String) request.getSession().getAttribute("trueMsgCode");
//
//        if(!authCode.equals(trueMsgCode)){
//            return "0";
//        }
//        User user = userService.findUserByPhone(phone);
//        if(user == null){
//            return "2";
//        }
//        MD5Util md5Util = new MD5Util();
//        String MD5Password = md5Util.encode(newPassword);
//        userService.updatePasswordByPhone(phone, MD5Password);
//
//        return "1";
//    }
//}
