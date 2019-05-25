package com.zyx.web;

import org.apache.ibatis.annotations.Param;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Author YuXingZh
 * @Date ：Created in 15:45 2019/5/25
 * @Description：
 */
@RestController
@RequestMapping("elasticsearch")
public class ElasticsearchController {
    @Autowired
    RestHighLevelClient client;

    @RequestMapping("addDocument")
    public void addDocument(@RequestBody Map<String, Object> map) throws IOException,
            ParseException {
        //创建添加文档的对象
        IndexRequest indexRequest = new IndexRequest("blog", "doc",
                (String) String.valueOf(map.get("id")));

        map.remove("createTime");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        map.put("createTime", dateFormat.format(new Date()));
        map.put("likes", 0);
        map.remove("tags");
        // 其他和和原json格式一致
        indexRequest.source(map);
        //创建client对象向ES提交请求
        //请求es添加文档
        client.index(indexRequest);
    }

    @RequestMapping("deleteDocument")
    public void deleteDocument(@RequestParam("articleId") String articleId) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("blog", "doc", articleId);
        client.delete(deleteRequest);
    }
}
