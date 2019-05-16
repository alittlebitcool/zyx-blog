package com.zyx.service.Impl;

import com.zyx.dao.TagArticleMapper;
import com.zyx.dao.TagMapper;
import com.zyx.entity.Tag;
import com.zyx.entity.vo.TagArticle;
import com.zyx.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by YuXingZh on 19-3-20
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagArticleMapper tagArticleMapper;

    @Autowired
    TagMapper tagMapper;

    /**
     * get a tag from Article
     * @param articleId
     * @return
     */
    @Override
    public List<String> getSpecialTag(int articleId) {
        TagArticle tagArticle = new TagArticle();
        tagArticle.setArticleId(articleId);
        List<TagArticle> tagArticles = tagArticleMapper.select(tagArticle);
        List<Integer> tagIds =
                tagArticles.stream().map(TagArticle::getTagId).collect(Collectors.toList());
        List<Tag> tags = tagMapper.selectByIdList(tagIds);
        return tags.stream().map(Tag::getName).collect(Collectors.toList());
    }
}