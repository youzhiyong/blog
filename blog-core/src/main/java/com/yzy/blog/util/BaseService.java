package com.yzy.blog.util;


import java.util.List;

/**
 * Description:  此接口可作为所有Service接口的基类使用
 * @see BaseDao
 * @see AbstractBaseServiceImpl
 * E : 对应的实体类; K : 对应主键类型
 * Date: 2018-06-06
 * @author youzhiyong
 */
public interface BaseService<E, K> {

    /**
     * 新增记录
     *
     * @param entity
     * @return
     * @throws Exception
     */
    int insert(E entity) throws Exception;

    /**
     * 批量新增记录
     *
     * @param entities
     * @return
     * @throws Exception
     */
    int insertBatch(List<E> entities) throws Exception;

    /**
     * 根据Id删除记录
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(K id);

    /**
     * 删除多条记录
     *
     * @param ids
     * @return
     */
    int deleteByPrimaryKeys(K[] ids);

    /**
     * 根据ID更新记录
     * 全部更新
     *
     * @param entity
     * @return
     */
    int updateByPrimaryKey(E entity) throws Exception;

    /**
     * 根据Id获取记录
     *
     * @param id
     * @return
     */
    E getByPrimaryKey(K id);
}
