package com.zyx;


import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.suggest.Suggester;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.List;
import java.util.Scanner;

/**
 * Dashboard仪表盘
 */

public class HystrixDashboardApplication {

    public static void main(String[] args) {
        System.out.println("------------------------------------功能1" +
                "语法拆分-----------------------------------");
        // 你好我是来自互联网金融二班的郑煜星
        Scanner in = new Scanner(System.in);
        String Program = in.next();
        System.out.println("下面为您进行语义拆分");
        List<Term> termList = StandardTokenizer.segment(Program);
        System.out.println(termList);


        System.out.println("------------------------------------功能2提取关键词-----------------------------------");
//        美国驻英国大使此前表示，作为英国退欧后任何贸易协议的一部分，英国将允许包括消毒鸡肉在内的美国农产品进入英国市场，以及美国私营部门参与英国国民健康保险制度(NHS)，此举引发了反对。据悉，特蕾莎⋅梅将于本周五（6月7日）正式辞职，英国政府将此次访问视为贸易重置的一个机会。据《卫报》报道，特朗普的妻子梅拉尼娅和四个成年子女将陪同特朗普进行为期三天的访问。特朗普将与特蕾莎⋅梅和英国女王一起出席在白金汉宫举行的国宴。工党领袖杰里米⋅科尔宾、下议院议长约翰⋅伯考和自由民主党领袖文斯⋅凯布尔爵士均拒绝出席。
        String content = in.next();
        List<String> keywordList = HanLP.extractKeyword(content, 5);
        System.out.println(keywordList);


        System.out.println("------------------------------------功能3自动摘要-----------------------------------");
//        算法可大致分为基本算法、数据结构的算法、数论算法、计算几何的算法、图的算法、动态规划以及数值分析、加密算法、排序算法、检索算法、随机化算法、并行算法、厄米变形模型、随机森林算法。算法可以宽泛的分为三类，一，有限的确定性算法，这类算法在有限的一段时间内终止。他们可能要花很长时间来执行指定的任务，但仍将在一定的时间内终止。这类算法得出的结果常取决于输入值。二，有限的非确定算法，这类算法在有限的时间内终止。然而，对于一个（或一些）给定的数值，算法的结果并不是唯一的或确定的。三，无限的算法，是那些由于没有定义终止定义条件，或定义的条件无法由输入的数据满足而不终止运行的算法。通常，无限算法的产生是由于未能确定的定义终止条件。
        String document = in.next();
        List<String> sentenceList = HanLP.extractSummary(document, 3);
        System.out.println(sentenceList);


        System.out.println("------------------------------------功能4短语提取-----------------------------------");
        Suggester suggester = new Suggester();
        String[] titleArray =
                (
                        "威廉王子发表演说 呼吁保护野生动物\n" +
                                "《时代》年度人物最终入围名单出炉 普京马云入选\n" +
                                "“黑格比”横扫菲：菲吸取“海燕”经验及早疏散\n" +
                                "日本保密法将正式生效 日媒指其损害国民知情权\n" +
                                "英报告说空气污染带来“公共健康危机”"
                ).split("\\n");
        for (String title : titleArray) {
            suggester.addSentence(title);
        }
        System.out.println("输入你想获取的信息");
        String information = in.next();
        int num = in.nextInt();
        System.out.println(suggester.suggest(information, num));
    }
}
