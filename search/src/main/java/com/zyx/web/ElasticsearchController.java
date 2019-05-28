package com.zyx.web;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author YuXingZh
 * @Date ：Created in 15:45 2019/5/25
 * @Description：
 */
@Controller
@RequestMapping("/elasticsearch")
public class ElasticsearchController {
    @Autowired
    RestHighLevelClient client;

    @RequestMapping("/addDocument")
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

    @RequestMapping("/deleteDocument")
    public void deleteDocument(@RequestParam("articleId") String articleId) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("blog", "doc", articleId);
        client.delete(deleteRequest);
    }

    @ResponseBody
    @RequestMapping("/keyWordsSearch")
    public List<Map<String, Object>> keyWordsSearch(@RequestParam("keyWords") String keyWords) throws IOException {
        SearchRequest searchRequest = new SearchRequest("blog");
        searchRequest.types("doc");

        //搜索builder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //定义高亮设置参数
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        // 设置前后缀
//        highlightBuilder.preTags("<tag style=\"color:red\">");
//        highlightBuilder.postTags("</tag>");
        //设置高亮字段
//        HighlightBuilder.Field highlightContent = new HighlightBuilder.Field(
//                "introduction");
//        highlightContent.preTags("<strong>").postTags("</strong>");
//        highlightBuilder.fields().add(highlightContent);

        highlightBuilder.fields().add(new HighlightBuilder.Field(
                "introduction").preTags("<tag style=\"color:pink\">").postTags(
                "</tag>"));

        highlightBuilder.fields().add(new HighlightBuilder.Field("title").preTags("<tag style=\"color:pink\">").postTags("</tag>"));

        searchSourceBuilder.highlighter(highlightBuilder);


        //指定包括哪些字段，不包括哪些字段
        searchSourceBuilder.fetchSource(new String[]{"id", "title",
                "introduction",
                "content", "createTime"}, new String[]{});
        //设置MultiMatchQuery
        MultiMatchQueryBuilder multiMatchQueryBuilder =
                QueryBuilders.multiMatchQuery(keyWords, "title",
                        "introduction", "content");
//        multiMatchQueryBuilder.minimumShouldMatch("70%")
        multiMatchQueryBuilder.field("title", 10).field("introduction", 5);


        //布尔查询builder
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(multiMatchQueryBuilder);

        //最终将布尔查询builder设置到searchSourceBuilder
        searchSourceBuilder.query(boolQueryBuilder);

        //分页参数
//        searchSourceBuilder.from(0);//起始记录下标，从0开始
//        searchSourceBuilder.size(1);//每页显示记录数
        //将searchSourceBuilder设置到searchRequest中
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest);
        //解析搜索结果
        //匹配结果信息
        SearchHits hits = searchResponse.getHits();
        //搜索出来数据
        SearchHit[] searchHits = hits.getHits();
        List<Map<String, Object>> res = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            //获取源文档内容
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            //取出高亮字段内容
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            //name字段高亮结果
            HighlightField highlightIntroduction = highlightFields.get(
                    "introduction");
            if (highlightIntroduction != null) {
                Text[] fragments = highlightIntroduction.getFragments();
                StringBuffer name = new StringBuffer();
                for (Text text : fragments) {
                    String string = text.string();
                    name.append(string);
                }
                sourceAsMap.put("introduction", name);
            }


            HighlightField highlightTitle = highlightFields.get(
                    "title");
            if (highlightTitle != null) {
                Text[] fragments = highlightTitle.getFragments();
                StringBuffer name = new StringBuffer();
                for (Text text : fragments) {
                    String string = text.string();
                    name.append(string);
                }
                sourceAsMap.put("title", name);
            }
            res.add(sourceAsMap);
        }
        return res;
    }
}
