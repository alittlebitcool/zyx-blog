package com.zyx.web;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleControllerTest {
    @Autowired
    RestHighLevelClient client;

    @Test
    public void testCreateIndex() throws Exception {
        //用于创建索引库的对象，指定索引库名称
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("blog");

        //设置索引库的分片数量及副本的数量
        createIndexRequest.settings(
                Settings.builder().put("number_of_shards", 1)//索引库的分片数量
                        .put("number_of_replicas", 0));
        //参数：String type, String source, XContentType xContentType
        String mapping_source = "{\"properties\":{\"id\":{\"type\":\"integer\"},\"title\":{\"type\":\"text\",\"analyzer\":\"hanlp_index\",\"search_analyzer\":\"hanlp_index\"},\"introduction\":{\"type\":\"text\",\"analyzer\":\"hanlp_nlp\",\"search_analyzer\":\"hanlp_nlp\"},\"content\":{\"type\":\"text\",\"analyzer\":\"hanlp_nlp\",\"search_analyzer\":\"hanlp_nlp\"},\"createTime\":{\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\"},\"likes\":{\"type\":\"integer\"}}}";
        createIndexRequest.mapping("doc", mapping_source, XContentType.JSON);

        //创建IndicesClient对象向ES提交请求
        IndicesClient indices = client.indices();
        //创建索引库
        CreateIndexResponse createIndexResponse = indices.create(createIndexRequest);
        //得到响应的结果
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println(acknowledged);

    }

    @Test
    public void testDeleteIndex() throws IOException {
        //用于创建索引库的对象，指定索引库名称
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(
                "school");

        //创建IndicesClient对象向ES提交请求
        IndicesClient indices = client.indices();
        //创建索引库
        DeleteIndexResponse deleteIndexResponse = indices.delete(deleteIndexRequest);
        //得到响应的结果
        boolean acknowledged = deleteIndexResponse.isAcknowledged();
        System.out.println(acknowledged);
    }

    @Test
    public void test() {
        String mapping_source = " {\n" +
                " \t\"properties\": {\n" +
                "           \"name\": {\n" +
                "              \"type\": \"text\",\n" +
                "              \"analyzer\":\"ik_max_word\",\n" +
                "              \"search_analyzer\":\"ik_smart\"\n" +
                "           },\n" +
                "           \"description\": {\n" +
                "              \"type\": \"text\",\n" +
                "              \"analyzer\":\"ik_max_word\",\n" +
                "              \"search_analyzer\":\"ik_smart\"\n" +
                "           },\n" +
                "           \"studymodel\": {\n" +
                "              \"type\": \"keyword\"\n" +
                "           },\n" +
                "           \"price\": {\n" +
                "              \"type\": \"float\"\n" +
                "           },\n" +
                "           \"timestamp\": {\n" +
                "          \t\t\"type\":   \"date\",\n" +
                "          \t\t\"format\": \"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\"\n" +
                "        \t}\n" +
                "        }\n" +
                "}";
        System.out.println(mapping_source);
    }

    /**
     *
     * 创建文档（索引）
     */
    @Test
    public void testAddDoc() throws IOException {
        //创建添加文档的对象
        IndexRequest indexRequest = new IndexRequest("blog", "doc");

        //文档的内容，就是map格式
        //准备json数据
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("id", 22);
        jsonMap.put("title", "郑煜星的个人宿舍");
        jsonMap.put("introduction", "郑煜星的所在位置是哪里，这是一个问题");
        jsonMap.put("content", "郑煜星的宿舍是在厦门软件学院的E幢404，是个人杰地灵的地方");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jsonMap.put("createTime", dateFormat.format(new Date()));
        jsonMap.put("likes", 0);
        indexRequest.source(jsonMap);
        //创建client对象向ES提交请求
        //请求es添加文档
        IndexResponse indexResponse = client.index(indexRequest);
        //获取响应结果
        DocWriteResponse.Result result = indexResponse.getResult();
        System.out.println(result);
    }

    /**
     * 查询文档
     * @throws IOException
     */
    @Test
    public void testGetDoc() throws IOException {
        //创建添加文档的对象
        GetRequest getRequest = new GetRequest("blog", "doc", "sVfj3moB1kN2I0ZGJYq8");


        //创建client对象向ES提交请求
        //请求es查询文档
        GetResponse getResponse = client.get(getRequest);
        boolean exists = getResponse.isExists();
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
        System.out.println(sourceAsMap);
    }

    @Test
    public void testUpdateDoc() throws IOException {
//        //创建添加文档的对象
//        GetRequest getRequest = new GetRequest("blog","doc","2");
//
//        //创建client对象向ES提交请求
//        //请求es查询文档
//        GetResponse getResponse = client.get(getRequest);
//        boolean exists = getResponse.isExists();
//        if (exists) {
        //更新
        UpdateRequest updateRequest = new UpdateRequest("blog", "doc", "slfp3moB1kN2I0ZG_oo6");
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("introduction", "springcloud的主要传输工具");
        updateRequest.doc(jsonMap);
        UpdateResponse updateResponse = client.update(updateRequest);
        DocWriteResponse.Result result = updateResponse.getResult();
        System.out.println(result);
//        }
    }
}