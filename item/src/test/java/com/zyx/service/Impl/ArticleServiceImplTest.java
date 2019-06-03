package com.zyx.service.Impl;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;
import com.hankcs.hanlp.suggest.Suggester;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.zyx.dao.ArticleMapper;
import com.zyx.dao.TagArticleMapper;
import com.zyx.dao.TagMapper;
import com.zyx.entity.Article;
import com.zyx.entity.Tag;
import com.zyx.entity.vo.TagArticle;
import com.zyx.service.ArticleService;
import com.zyx.service.CommentService;
import com.zyx.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by YuXingZh on 19-3-21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceImplTest {

    @Autowired
    ArticleService articleService;
//
//    @Autowired
//    TagService tagService;

    @Autowired
    CommentService commentService;

    @Autowired
    TagService tagService;

    @Test
    public void test() {

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        CoNLLSentence sentence = HanLP.parseDependency("徐先生还具体帮助他确定了把画雄鹰、松鼠和麻雀作为主攻目标。");
        System.out.println(sentence);
        // 可以方便地遍历它
        for (CoNLLWord word : sentence) {
            System.out.printf("%s --(%s)--> %s\n", word.LEMMA, word.DEPREL, word.HEAD.LEMMA);
        }
        // 也可以直接拿到数组，任意顺序或逆序遍历
        CoNLLWord[] wordArray = sentence.getWordArray();
        for (int i = wordArray.length - 1; i >= 0; i--) {
            CoNLLWord word = wordArray[i];
            System.out.printf("%s --(%s)--> %s\n", word.LEMMA, word.DEPREL, word.HEAD.LEMMA);
        }
        // 还可以直接遍历子树，从某棵子树的某个节点一路遍历到虚根
        CoNLLWord head = wordArray[12];
        while ((head = head.HEAD) != null) {
            if (head == CoNLLWord.ROOT) System.out.println(head.LEMMA);
            else System.out.printf("%s --(%s)--> ", head.LEMMA, head.DEPREL);
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    @Autowired
    TagArticleMapper tagArticleMapper;

    @Autowired
    TagMapper tagMapper;

    @Test
    public void test1() {
//        System.out.println(commentService.getAllComment(11));
//        System.out.println(tagService.getSpecialTag(11));
        TagArticle tagArticle = new TagArticle();
        tagArticle.setArticleId(11);
        List<TagArticle> tagArticles = tagArticleMapper.select(tagArticle);
        System.out.println(tagArticles);
        List<Integer> tagIds =
                tagArticles.stream().map(TagArticle::getTagId).collect(Collectors.toList());
        System.out.println(tagArticles.stream().map(TagArticle::getTagId).collect(Collectors.toList()));
        List<Tag> tags = tagMapper.selectByIdList(tagIds);
        System.out.println(tags.stream().map(Tag::getName).collect(Collectors.toList()));
    }

    @Autowired
    ArticleMapper articleMapper;

    @Test
    public void test2() {
        Suggester suggester = new Suggester();
        List<Article> articles = articleMapper.selectAll();
        List<String> introductions =
                articles.stream().map(i -> i.getIntroduction()).distinct().collect(Collectors.toList());
        for (String introduction : introductions) {
            suggester.addSentence(introduction);
        }

        List<String> suggest = suggester.suggest("thymeleaf教程", 2);
        List<Article> res = new ArrayList<>();
        for (Article article : articles) {
            if (article.getIntroduction().equals(suggest.get(0)) && res.size() < 2) {
                res.add(article);
            }
            if (article.getIntroduction().equals(suggest.get(1)) && res.size() < 2) {
                res.add(article);
            }
        }
        System.out.println(res);
    }

    @Test
    public void test3() throws ParseException {
        Article article = new Article();
        article.setId(11);
        article.setTitle("title");
        article.setContent("content");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        article.setModifyTime(new Date());
        article.setIntroduction("introduction");
        Map<String, Object> map = new HashMap<>();
        map.put("id", "83");
        map.put("tags", "test");
        map.put("title", "test");
        map.put("content", "test");
        map.put("instroduction", "test");
        map.put("createTime", "1029-01-10");
        articleService.deleteArticle(85);
        Tag tag = new Tag();
        tag.setName("这是一个");
        Example example = new Example(Tag.class);
        example.and().andEqualTo("name","博客好吧");
        tagMapper.selectByExample(example);
        List<Integer> ids =
                tagMapper.selectByExample(example).stream().map(Tag::getId).collect(Collectors.toList());
        List<TagArticle> tagArticles = new ArrayList<>();
        for (int i : ids) {
            Example example1 = new Example(TagArticle.class);
            example1.and().andEqualTo("tagId",i);
            tagArticles.add(tagArticleMapper.selectOneByExample(example1));
        }
        List<Article> articles = articleMapper.selectByIdList(tagArticles.stream().map(TagArticle::getArticleId).collect(Collectors.toList()));

        System.out.println(articles);

    }

    @Test
    public void test4() {
        System.out.println(commentService.getSpecialComment(111));
    }

    @Test
    public void keywordExtraction() {
//        Scanner s = new Scanner(System.in);

        String content = "程序员(英文Programmer)是从事程序开发、维护的专业人员。一般将程序员分为程序设计人员和程序编码人员，但两者的界限并不非常清楚，特别是在中国。软件从业人员分为初级程序员、高级程序员、系统分析员和项目经理四大类。";
        List<String> keywordList = HanLP.extractKeyword(content, 5);
        System.out.println(keywordList);

        System.out.println(NLPTokenizer.segment("我新造一个词叫幻想乡你能识别并标注正确词性吗？"));
// 注意观察下面两个“希望”的词性、两个“晚霞”的词性
        System.out.println(NLPTokenizer.analyze("我的希望是希望张晚霞的背影被晚霞映红").translateLabels());
        System.out.println(NLPTokenizer.analyze("支援臺灣正體香港繁體：微软公司於1975年由比爾·蓋茲和保羅·艾倫創立。"));
    }
}