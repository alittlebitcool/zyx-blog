package com.zyx.dao;

import com.zyx.entity.Tag;
import com.zyx.entity.vo.TagArticle;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author YuXingZh
 * @Date ：Created in 12:59 2019/5/16
 * @Description：
 */
public interface TagMapper extends Mapper<Tag>, SelectByIdListMapper<Tag,
        Integer> {

    /**
     * select all tags
     * @return
     */
    @Select("SELECT DISTINCT name FROM tag")
    List<String> selectAllTags();
}
