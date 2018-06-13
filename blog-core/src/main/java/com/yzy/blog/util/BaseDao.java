package com.yzy.blog.util;

import java.util.List;

/**
 * Description: 此接口可作为所有DAO接口的基类使用，不需要在mapper文件中定义所有的操作
 * @see BaseService   所有Service接口的基类
 * @see AbstractBaseServiceImpl    所有ServiceImpl实现类的抽象基类
 *  1. 可避免大量重复定义的工作
 *  2. 可统一DAO层的方法命名
 *  E : 对应的实体类; K : 对应主键类型
 * Date: 2018-06-06
 * @author youzhiyong
 */
public interface BaseDao<E, K> {

    /**
     * 新增记录
     *
     * @param entity
     * @return
     */
    int insert(E entity);

    /**
     * 批量新增记录
     *
     * @param entities
     * @return
     */
    int insertBatch(List<E> entities);

    /**
     * 根据主键删除记录
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(K id);

    /**
     * 批量删除记录
     *
     * @param ids
     * @return
     */
    int deleteBatch(K[] ids);

    /**
     * 根据主键全部更新记录
     *
     * @param entity
     * @return
     */
    int updateByPrimaryKey(E entity);

    /**
     * 根据主键查询记录
     *
     * @param id
     * @return
     */
    E selectByPrimaryKey(K id);
}
