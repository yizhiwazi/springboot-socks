package com.hehe.tools;

import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 主要用途：通用Service 封装常用的CRUD方法
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private Mapper<T> mapper;//泛型装配

    @Override
    public List<T> list(T entity) {
        return mapper.select(entity);
    }

    @Override
    public T get(T entity) {
        return  mapper.selectOne(entity);
    }

    @Override
    public int update(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int save(T entity) {
        return mapper.insertSelective(entity);
    }

    @Override
    public int delete(T entity) {
        return mapper.deleteByPrimaryKey(entity);
    }
}
