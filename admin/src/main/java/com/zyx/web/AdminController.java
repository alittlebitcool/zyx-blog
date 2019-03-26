package com.zyx.web;

import com.zyx.entity.User;
import com.zyx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by YuXingZh on 19-3-19
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
    @GetMapping(value = "/validate")
    public String detail(Model model,
                         HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user;
        if (email == null || password == null) return "login";
        user = new User(email, password);
        User u = userService.checkUser(user);
        if (u == null) return "login";
        model.addAttribute("user", u);
        request.getSession().setAttribute("user", u);
        return "detail";
    }
}
