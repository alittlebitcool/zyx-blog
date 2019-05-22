//package com.zyx.web;
//
//import com.zyx.entity.Article;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @Author YuXingZh
// * @Date ：Created in 16:40 2019/5/17
// * @Description：
// */
//@RestController
//@RequestMapping
//public class ArticleController {
//    /**
//     * 根据商品分类id查询名称
//     * @param ids 要查询的分类id集合
//     * @return 多个名称的集合
//     */
//    @GetMapping("names")
//    public List<Article> queryNameByIds(@RequestParam("ids") List<Long> ids){
//        List<String > list = this.categoryService.queryNameByIds(ids);
//        if (list == null || list.size() < 1) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return ResponseEntity.ok(list);
//    }
//}
