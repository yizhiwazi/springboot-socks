package com.hehe.common;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * 本类简介：通用存储库
 * 主要作用：
 * 1.继承JPA内置的CRUD实现 2.编写自定义的CRUD实现
 *
 * @see SimpleJpaRepository
 * @see CommonRepository
 */
@Transactional
public class CommonRepositoryImpl<T, PK extends Serializable>
        extends SimpleJpaRepository<T, PK> implements CommonRepository<T, PK> {


    private final EntityManager entityManager; //实体管家

    //添加构造器
    @SuppressWarnings("all")
    public CommonRepositoryImpl(JpaEntityInformation<T,PK> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public List<T> list() {
        return findAll();
    }

    @Override
    public T get(T entity) {
        return null;
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
