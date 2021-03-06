package com.zyx.dao;

import com.zyx.entity.Comment;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by YuXingZh on 19-3-19
 */
public interface CommentMapper extends Mapper<Comment>,
        SelectByIdListMapper<Comment, Integer> {
}
