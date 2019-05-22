package com.zyx.service.Impl;

import com.zyx.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author YuXingZh
 * @Date ：Created in 15:23 2019/5/18
 * @Description：
 */
@Service
public class ArticleServiceImpl implements ArticleService {
//    private static final Logger LOGGER = LoggerFactory.getLogger(CityESServiceImpl.class);
    Integer PAGE_SIZE = 12;
    Integer DEFAULT_PAGE_NUMBER = 0;
    String SCORE_MODE_SUM = "sum";
    Float  MIN_SCORE = 10.0F;
//    @Autowired
//    CityRepository cityRepository;
}
