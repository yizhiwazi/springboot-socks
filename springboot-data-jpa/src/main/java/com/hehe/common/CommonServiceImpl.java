package com.hehe.common;

import java.io.Serializable;
import java.util.List;

/**
 * {@link CommonService 提供通用的CRUD方法}
 * {@link CommonService 提供通用的CRUD方法}
 *
 * @param <T> 实体类
 * @param <PK> 主键类型
 */


public class CommonServiceImpl<T, PK extends Serializable> implements CommonService<T> {


    @Override
    public List<T> list() {
        return null;
    }

    @Override
    public T get(T entity) {
        return null;
    }

    @Override
    public boolean save(T entity) {
        return false;
    }

    @Override
    public boolean update(T entity) {
        return false;
    }

    @Override
    public boolean deleteBatch(List<T> entities) {
        return false;
    }
}
