package com.zyx.web;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author YuXingZh
 * @Date ：Created in 17:44 2019/5/22
 * @Description：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTest {

    @Autowired
    RestHighLevelClient client;

    /**
     *
     * 查询全部
     */
    @Test
    public void testMathch_all() throws IOException {
        //创建搜索请求对象, 指定索引库名称
        SearchRequest searchRequest = new SearchRequest("blog");
        searchRequest.types("doc");//指定类型

        //搜索builder
        SearchSourceBuilder searchSourceBuilder =new SearchSourceBuilder();
        //指定包括哪些字段，不包括哪些字段
        searchSourceBuilder.fetchSource(new String[]{"id", "title",
                        "introduction", "content", "create_time",
                        "likes"}, new String[]{});
        //设置搜索方式
        //全部搜索
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //分页参数
        searchSourceBuilder.from(0);//起始记录下标，从0开始
        searchSourceBuilder.size(4);//每页显示记录数
        //将searchSourceBuilder设置到searchRequest中
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest);
        //解析搜索结果
        //匹配结果信息
        SearchHits hits = searchResponse.getHits();
        //搜索出来数据
        SearchHit[] searchHits = hits.getHits();
        for(SearchHit hit:searchHits){
            //获取源文档内容
            String id = hit.getId();
            System.out.println(id);
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap);
        }
    }

    /**
     * 精确查询，不对查询条件进行分词
     * @throws IOException
     */
    @Test
    public void testTermQuery() throws IOException {
        //创建搜索请求对象, 指定索引库名称
        SearchRequest searchRequest = new SearchRequest("blog");
        searchRequest.types("doc");//指定类型

        //搜索builder
        SearchSourceBuilder searchSourceBuilder =new SearchSourceBuilder();
        //指定包括哪些字段，不包括哪些字段
        searchSourceBuilder.fetchSource(new String[]{"id", "title",
                "introduction", "content", "create_time",
                "likes"}, new String[]{});
        //设置搜索方式
        //基本term query
//        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("name", "开发");
        String[] ids = new String[]{"22"};
        List<String> idList = Arrays.asList(ids);
        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("_id",idList);
        searchSourceBuilder.query(termsQueryBuilder);
        //分页参数
        searchSourceBuilder.from(0);//起始记录下标，从0开始
        searchSourceBuilder.size(1);//每页显示记录数
        //将searchSourceBuilder设置到searchRequest中
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest);
        //解析搜索结果
        //匹配结果信息
        SearchHits hits = searchResponse.getHits();
        //搜索出来数据
        SearchHit[] searchHits = hits.getHits();
        for(SearchHit hit:searchHits){
            //获取源文档内容
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap);
        }
    }

    /**
     * 指定占比的特殊搜索
     * @throws IOException
     */
    @Test
    public void testMathchQuery() throws IOException {
        //创建搜索请求对象, 指定索引库名称
        SearchRequest searchRequest = new SearchRequest("blog");
        searchRequest.types("doc");//指定类型

        //搜索builder
        SearchSourceBuilder searchSourceBuilder =new SearchSourceBuilder();
        //指定包括哪些字段，不包括哪些字段
        searchSourceBuilder.fetchSource(new String[]{"id", "title",
                "introduction", "content", "create_time",
                "likes"}, new String[]{});
        //设置matchQuery
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(
                "introduction", "spring");
        matchQueryBuilder.operator(Operator.OR);
        matchQueryBuilder.minimumShouldMatch("70%");//三个词至少匹配两个
        searchSourceBuilder.query(matchQueryBuilder);
        //分页参数
        searchSourceBuilder.from(0);//起始记录下标，从0开始
        searchSourceBuilder.size(2);//每页显示记录数
        //将searchSourceBuilder设置到searchRequest中
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest);
        //解析搜索结果
        //匹配结果信息
        SearchHits hits = searchResponse.getHits();
        //搜索出来数据
        SearchHit[] searchHits = hits.getHits();
        for(SearchHit hit:searchHits){
            //获取源文档内容
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap);
        }
    }


    /**
     *  设置权重进行筛选
     * MultiMathchQuery
     */
    @Test
    public void testMultiMathchQuery() throws IOException {
        //创建搜索请求对象, 指定索引库名称
        SearchRequest searchRequest = new SearchRequest("blog");
        searchRequest.types("doc");//指定类型

        //搜索builder
        SearchSourceBuilder searchSourceBuilder =new SearchSourceBuilder();
        //指定包括哪些字段，不包括哪些字段
        searchSourceBuilder.fetchSource(new String[]{"title", "content",
                        "introduction"},
                new String[]{});
        //设置MultiMatchQuery
        MultiMatchQueryBuilder multiMatchQueryBuilder =
                QueryBuilders.multiMatchQuery("spring", "title",
                        "description");
//        multiMatchQueryBuilder.minimumShouldMatch("70%")
        multiMatchQueryBuilder.field("title",10);
        searchSourceBuilder.query(multiMatchQueryBuilder);


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
        for(SearchHit hit:searchHits){
            //获取源文档内容
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap);
        }
    }

    /**
     * 结合了设置权重和精确查询
     */
    //boolQuery
    @Test
    public void testBoolQuery() throws IOException {
        //创建搜索请求对象, 指定索引库名称
        SearchRequest searchRequest = new SearchRequest("blog");
        searchRequest.types("doc");//指定类型

        //搜索builder
        SearchSourceBuilder searchSourceBuilder =new SearchSourceBuilder();
        //指定包括哪些字段，不包括哪些字段
        searchSourceBuilder.fetchSource(new String[]{"title","introduction"},
                new String[]{});
        //设置MultiMatchQuery
        MultiMatchQueryBuilder multiMatchQueryBuilder =
                QueryBuilders.multiMatchQuery("郑煜星", "name", "description");
//        multiMatchQueryBuilder.minimumShouldMatch("70%")
        multiMatchQueryBuilder.field("name",10);


        //基于termQuery
        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("title",
                "郑煜星");

        //布尔查询builder
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(multiMatchQueryBuilder);
        boolQueryBuilder.must(termsQueryBuilder);


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
        for(SearchHit hit:searchHits){
            //获取源文档内容
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap);

        }


    }
}
