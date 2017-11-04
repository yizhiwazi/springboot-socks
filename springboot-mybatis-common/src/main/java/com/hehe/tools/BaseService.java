package com.hehe.tools;

import java.util.List;

/**
 * 通用Service
 */
public interface BaseService<T> {

    List<T> list(T entity);

    T get(T entity);

    int update(T entity);

    int save(T entity);

    int delete(T entity);

}
