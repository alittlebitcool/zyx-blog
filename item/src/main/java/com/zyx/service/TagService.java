package com.zyx.service;

import java.util.List;

/**
 * @Author YuXingZh
 * @Date ：Created in 12:13 2019/5/13
 * @Description：
 */
public interface TagService {

    /**
     * get a tag from Article
     * @param articleId
     * @return
     */
    List<String> getSpecialTag(int articleId);

    /**
     * add article's tags
     */
    void addTags(String tags, int articleId);
}
