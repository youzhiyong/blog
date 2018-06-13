package com.yzy.blog.util;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Description: 此类所有ServiceImpl实现类的抽象基类
 * @see BaseDao
 * @see BaseService
 * E : 对应的实体类; K : 对应主键类型; M : 继承于BaseDao的dao接口
 * Date: 2018-06-06
 * @author youzhiyong
 */
public abstract class AbstractBaseServiceImpl<E, K, M extends BaseDao<E, K>> implements BaseService<E, K> {

    @Autowired
    protected M mapper;

    /**
     * 新增记录
     *
     * @param entity
     * @return
     */
    @Override
    public int insert(E entity) throws Exception {
        int i = mapper.insert(entity);
        if (i == 0) {
            throw new Exception("Insert entity error");
        }
        return i;
    }

    /**
     * 新增记录
     *
     * @param entities
     * @return
     */
    @Override
    public int insertBatch(List<E> entities) throws Exception {
        int i = mapper.insertBatch(entities);
        if (i == 0) {
            throw new Exception("Insert entity error");
        }
        return i;
    }

    /**
     * 根据Id删除记录
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(K id) {
        return mapper.deleteByPrimaryKey(id);
    }

    /**
     * 删除多条记录
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteByPrimaryKeys(K[] ids) {
        if (ids.length == 0) {
            return 0;
        }
        return mapper.deleteBatch(ids);
    }

    /**
     * 根据ID更新记录
     * 全部更新
     *
     * @param entity
     * @return
     */
    @Override
    public int updateByPrimaryKey(E entity) throws Exception {
        if (null == entity) {
            throw new IllegalArgumentException("Entity can not be null");
        }
        int i = mapper.updateByPrimaryKey(entity);
        if (i == 0) {
            throw new Exception("update error");
        }
        return i;
    }

    /**
     * 根据Id获取记录
     *
     * @param id
     * @return
     */
    @Override
    public E getByPrimaryKey(K id) {
        if (null == id) {
            throw new IllegalArgumentException("Key can not be null");
        }
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 获取基础mapper
     *
     * @return
     */
    public M getBaseMapper() {
        return mapper;
    }

}
