package com.hehe.common;

import java.util.List;

/**
 *  通用Service
 */

public interface CommonService<T> {

    T get(T entity);

    List<T> list(T entity);

    int update(T entity);

    int save(T entity);

    int deleteBatch(List<T> entityList);

}
