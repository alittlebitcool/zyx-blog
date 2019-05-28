package com.zyx.web;

import com.zyx.entity.Article;
import com.zyx.entity.Comment;
import com.zyx.service.ArticleService;
import com.zyx.service.CommentService;
import com.zyx.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author YuXingZh
 * @Date ：Created in 15:37 2019/5/12
 * @Description：blog's detail page
 */
@Controller
@RequestMapping("/details")
public class DetailController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private TagService tagService;

    /**
     * get all comment from a article
     *
     * @param articleId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAllComment")
    public List<Comment> getAllComment(@RequestParam("articleId") int articleId) {
        return commentService.getAllComment(articleId);
    }

    @GetMapping("/showArticle")
    public String showArticle(@RequestParam("articleId") int articleId, Model model) {
        Article article = articleService.getSpecial(articleId);
        model.addAttribute("article", article);
        model.addAttribute("comments", commentService.getAllComment(articleId));
        model.addAttribute("tags", tagService.getSpecialTag(articleId));
        List<Article> recommendArticle = articleService.recommendArticle(article.getTitle());
        if (recommendArticle.size() == 2) {
            model.addAttribute("recommend1", recommendArticle.get(0));
            model.addAttribute("recommend2", recommendArticle.get(1));
        }
        return "detail";
    }
}
