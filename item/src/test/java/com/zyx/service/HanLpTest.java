package com.zyx.service;


import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Scanner;

/**
 * @Author YuXingZh
 * @Date ：Created in 17:44 2019/5/22
 * @Description：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HanLpTest {
    @Test
    public void keywordExtraction() {
        Scanner in=new Scanner(System.in);
        String Program = in.next();
        System.out.println("下面为您进行语义拆分");
        List<Term> termList = StandardTokenizer.segment(Program);

        System.out.println(termList);

        String content = "程序员(英文Programmer)是从事程序开发、维护的专业人员。一般将程序员分为程序设计人员和程序编码人员，但两者的界限并不非常清楚，特别是在中国。软件从业人员分为初级程序员、高级程序员、系统分析员和项目经理四大类。";
        List<String> keywordList = HanLP.extractKeyword(content, 5);
        System.out.println(keywordList);
    }


}
