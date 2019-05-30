package com.zyx.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @Author YuXingZh
 * @Date ：Created in 17:22 2019/5/25
 * @Description：
 */
@FeignClient(name = "elasticsearch", url = "localhost:9600/elasticsearch")
public interface ElasticsearchFegin {

    @RequestMapping(value = "/addDocument",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    void addDocument(@RequestBody Map<String, Object> map) throws IOException,
            ParseException;

    @RequestMapping("/deleteDocument")
    void deleteDocument(@RequestParam("articleId") String articleId) throws IOException;
}
