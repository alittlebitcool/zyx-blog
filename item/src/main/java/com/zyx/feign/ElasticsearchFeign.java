package com.zyx.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author YuXingZh
 * @Date ：Created in 15:52 2019/5/27
 * @Description：
 */
@FeignClient(name = "elasticsearch", url = "http://localhost:9600/elasticsearch")
public interface ElasticsearchFeign {

    @ResponseBody
    @RequestMapping("keyWordsSearch")
    List<Map<String, Object>> keyWordsSearch(@RequestParam("keyWords") String keyWords) throws IOException;
}
