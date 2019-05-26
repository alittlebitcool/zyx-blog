package com.zyx.web;

import com.zyx.entity.User;
import com.zyx.feign.ArticleFeign;
import com.zyx.feign.ElasticsearchFegin;
import com.zyx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

/**
 * Created by YuXingZh on 19-3-19
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    ElasticsearchFegin elasticsearchFegin;

    @Autowired
    ArticleFeign articleFeign;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/validate")
    public String validate(Model model,
                           HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user;
        if (email == null || password == null) {
            return "login";
        }
        user = new User(email, password);
        User u = userService.checkUser(user);
        if (u == null) {
            return "login";
        }
        model.addAttribute("blogList", articleFeign.showAll());
        return "index";
    }

    @GetMapping(value = "/index")
    public String index(Model model,
                        HttpServletRequest request) {
        model.addAttribute("blogList", articleFeign.showAll());
        return "index";
    }


    @GetMapping(value = "/addArticle")
    public String detail(Model model,
                         HttpServletRequest request) {
        return "detail";
    }

    @RequestMapping(value = "/deleteArticle", method = RequestMethod.GET)
    public String deleteArticle(Model model,
                                HttpServletRequest request,
                                @RequestParam("articleId") int articleId) throws IOException {
        articleFeign.deleteArticle(articleId);
        elasticsearchFegin.deleteDocument(String.valueOf(articleId));
        model.addAttribute("blogList", articleFeign.showAll());
        return "/index";
    }

    @RequestMapping(value = "/addArticle", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String addArticle(Model model,
                             HttpServletRequest request, @RequestBody Map<String, Object> map) throws IOException,ParseException {
        model.addAttribute("blogList", articleFeign.showAll());
        int articleId = articleFeign.addArticle(map);
        map.put("id", articleId);
        elasticsearchFegin.addDocument(map);
        return "/index";
    }
}
