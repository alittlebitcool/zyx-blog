package com.zyx.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @Author YuXingZh
 * @Date ：Created in 17:22 2019/5/25
 * @Description：
 */
@FeignClient(name = "elasticsearch", url = "http://localhost:9600" +
        "/elasticsearch")
public interface ElasticsearchFegin {

    @RequestMapping("addDocument")
    public void addDocument(@RequestBody Map<String, Object> map) throws IOException,
            ParseException;

    @RequestMapping("deleteDocument")
    public void deleteDocument(@RequestParam("articleId") String articleId) throws IOException;
}
