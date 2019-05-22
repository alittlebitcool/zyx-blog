//package com.zyx.web;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.inject.Inject;
//import java.io.IOException;
//
///**
// * @Author YuXingZh
// * @Date ：Created in 16:38 2019/5/21
// * @Description：
// */
//@RestController
//@RequestMapping("/elastic")
//public class ElasticController {
//    @Inject
//    private RestHighLevelClient client;
//
//    /**
//     * 测试查询文档
//     */
//    @RequestMapping("/fetchIndex")
//    public Object getIndexTest() {
//        GetRequest request = new GetRequest("magic", "employee", "1");
//        try {
//            GetResponse response = client.get(request);
//            System.out.println(response);
//            return request;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
//
