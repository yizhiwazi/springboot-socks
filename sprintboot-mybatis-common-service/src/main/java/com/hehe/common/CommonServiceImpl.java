package com.hehe.common;


import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 通用ServiceImpl  封装通用Mapper的CRUD
 */

public class CommonServiceImpl<T> implements CommonService<T> {

    @Autowired
    private Mapper<T> mapper;//泛型装配

    @Override
    public T get(T entity) {
        List<T> list = mapper.select(entity);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<T> list(T entity) {
        return mapper.select(entity);
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
    public int deleteBatch(List<T> entityList) {
        int result = 0;
        for (T entity : entityList) {
            result += mapper.delete(entity);
        }
        return result;
    }
}
