package com.demo.mapper.simple;

import java.util.List;

import com.demo.mapper.BaseMapper;
import com.demo.model.simple.News;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/** 
 * @Description 新闻mapper接口
 * @author 王鑫 
 * @date Mar 16, 2017 3:35:19 PM  
 */
@Mapper
public interface NewsMapper extends BaseMapper<String, News> {

    List<News> findNewsByKeywords(@Param("keywords") String keywords);

    List<News> findNewsByPage(@Param("keywords") String keywords);

}
