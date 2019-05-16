package com.zyx.dao;

import com.zyx.entity.Tag;
import com.zyx.entity.vo.TagArticle;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author YuXingZh
 * @Date ：Created in 12:59 2019/5/16
 * @Description：
 */
public interface TagMapper extends Mapper<Tag>, SelectByIdListMapper<Tag,
        Integer> {
}
