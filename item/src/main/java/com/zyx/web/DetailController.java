package com.zyx.web;

import com.zyx.entity.Article;
import com.zyx.entity.Comment;
import com.zyx.service.ArticleService;
import com.zyx.service.CommentService;
import com.zyx.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
    public List<Comment> getSpecialComment(@RequestParam("articleId") int articleId) {
        return commentService.getSpecialComment(articleId);
    }

    @GetMapping("/showArticle")
    public String showArticle(@RequestParam("articleId") int articleId, Model model) {
        Article article = articleService.getSpecial(articleId);
        model.addAttribute("article", article);
        model.addAttribute("comments", commentService.getSpecialComment(articleId));
        model.addAttribute("tags", tagService.getSpecialTag(articleId));
        model.addAttribute("id", articleId);
        List<Article> recommendArticle = articleService.recommendArticle(article.getTitle());
        if (recommendArticle.size() == 2) {
            model.addAttribute("recommend1", recommendArticle.get(0));
            model.addAttribute("recommend2", recommendArticle.get(1));
        }
        return "detail";
    }

    /**
     * add comment
     */
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addComment(Model model,
                             HttpServletRequest request, @RequestBody Map<String, Object> map) {
        int articleId = commentService.addComment(map);
        Article article = articleService.getSpecial(articleId);
        model.addAttribute("article", article);
        model.addAttribute("comments", commentService.getSpecialComment(articleId));
        model.addAttribute("tags", tagService.getSpecialTag(articleId));
        model.addAttribute("id", articleId);
        List<Article> recommendArticle = articleService.recommendArticle(article.getTitle());
        if (recommendArticle.size() == 2) {
            model.addAttribute("recommend1", recommendArticle.get(0));
            model.addAttribute("recommend2", recommendArticle.get(1));
        }
        return "detail";
    }
}
