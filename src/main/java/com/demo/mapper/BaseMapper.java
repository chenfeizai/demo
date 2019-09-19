package com.demo.mapper;

import java.util.List;
import java.util.Map;

/**
 * @param <S>
 * @param <T>
 * @author 王鑫
 * @Description IBaseMapper
 * @date Mar 16, 2017 5:18:48 PM
 */
public interface BaseMapper<S, T> {

    /**
     * @param pk
     * @return
     * @Title 根据ID查询
     */
    T findById(S pk);

    /**
     * @param t
     * @return
     * @throws Exception
     * @Title 根据对象插入数据
     * @author <font color="green"><b>Vincent.wang</b></font>
     */
    int insert(T t);

    /**
     * @param pk
     * @return
     * @throws Exception
     * @Title 根据PK删除
     * @author <font color="green"><b>Vincent.wang</b></font>
     */
    int delete(S pk);

    /**
     * @param t
     * @return
     * @throws Exception
     * @Title 根据用户修改
     * @author <font color="green"><b>Vincent.wang</b></font>
     */
    int update(T t);

    /**
     * @param map
     * @return Map
     * @throws Exception
     * @Title 根据Map查找单个
     * @author <font color="green"><b>Vincent.wang</b></font>
     */
    Map<String, Object> queryOne(Map<String, Object> map);

    /**
     * @param map
     * @return List<Map>
     * @throws Exception
     * @Title 根据Map查找多个
     * @author <font color="green"><b>Vincent.wang</b></font>
     */
    List<Map<String, Object>> queryAll(Map<String, Object> map);

    /***
     * @Title 根据条件查询数据
     * @param map
     *            查询条件
     * @return 对象集合
     */
    List<T> findAllByFilter(Map<String, Object> map);

    /**
     * @param map 查询条件
     * @return 记录数
     * @Title 根据条件查询数据记录数
     */
    int findAllByFilterCount(Map<String, Object> map);

}