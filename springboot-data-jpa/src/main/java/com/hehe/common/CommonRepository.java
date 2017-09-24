package com.hehe.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * 本类简介：通用存储库
 * 主要作用：
 * 1.继承JPA内置的CRUD接口 2.编写自定义的CRUD接口
 * @see JpaRepository
 * @see CommonRepositoryImpl
 */


@NoRepositoryBean //中间接口 无需实例化
public interface CommonRepository<T, PK extends Serializable> extends JpaRepository<T, PK> {

    List<T> list();

    T get(T entity);

    boolean update( T entity);

    boolean deleteBatch( List<T> entities);

}
