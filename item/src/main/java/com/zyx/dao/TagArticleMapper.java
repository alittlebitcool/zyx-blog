package com.zyx.dao;

import com.zyx.entity.Tag;
import com.zyx.entity.vo.TagArticle;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;

/**
 * Created by YuXingZh on 19-3-19
 */
public interface TagArticleMapper extends Mapper<TagArticle>,
        SelectByIdListMapper<Tag, Integer> {

}
