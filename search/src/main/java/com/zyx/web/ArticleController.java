//package com.zyx.web;
//
//import com.zyx.dao.ArticleRepository;
//import com.zyx.entity.Article;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.index.query.QueryStringQueryBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Optional;
//
///**
// * @Author YuXingZh
// * @Date ：Created in 16:40 2019/5/17
// * @Description：
// */
//@RestController
//@RequestMapping("/Article")
//public class ArticleController {
//
//    @Autowired
//    private ArticleRepository articleRepository;
//
//    /**
//     * 保存
//     * @param name
//     * @param description
//     * @return
//     */
//    @RequestMapping("/save")
//    public String save(String name, String description){
//        Article Article = new Article(System.currentTimeMillis(), name, description);
//        articleRepository.save(com.zyx.entity.Article);
//        return "success";
//    }
//
//    /**
//     * 更新
//     * @param id
//     * @param name
//     * @param description
//     * @return
//     */
//    @RequestMapping("/update")
//    public String update(Long id, String name, String description){
//        Article Article = new Article(id, name, description);
//        articleRepository.save(com.zyx.entity.Article);
//        return "success";
//    }
//
//    /**
//     * 根据id查找
//     * @param id
//     * @return
//     */
//    @RequestMapping("/selectOne")
//    public Optional<Article> selectOne(Long id){
//        return articleRepository.findById(id);
//    }
//
//    /**
//     * 删除
//     * @param id
//     * @return
//     */
//    @RequestMapping("/delete")
//    public String delete(Long id){
//        articleRepository.deleteById(id);
//        return "success";
//    }
//
//    /**
//     * 限定关键词的搜索
//     * @param name
//     * @param pageable
//     * @return
//     */
//    @RequestMapping("/getArticleListByItem")
//    public List<Article> getArticleListByItem(String name, @PageableDefault(page=0,
//            value = 10) Pageable pageable){
//        //设置分页
//        QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", name);
//        Page<Article> ArticlePage = articleRepository.search(queryBuilder, pageable);
//        return ArticlePage.getContent();
//    }
//
//    /**
//     * 模糊搜索
//     * @param condition
//     * @return
//     */
//    @RequestMapping("/getArticleList")
//    public List<Article> getArticleList(String condition){
//        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(condition);
//        Iterable<Article> searchResult = articleRepository.search(builder);
//        List<Article> ArticleList = new ArrayList<>();
//        Iterator<Article> iterator = searchResult.iterator();
//        while (iterator.hasNext()){
//            ArticleList.add(iterator.next());
//        }
//        return ArticleList;
//    }
//}