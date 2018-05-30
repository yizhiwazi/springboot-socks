package com.hehe.common;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * 通用JpaRepository实现类
 */

public class SimpleJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> {

    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager em;

    @Autowired
    public SimpleJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
    }

    /**
     * 通用save方法 ：新增/选择性更新
     */
    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        //获取ID
        ID entityId = (ID) entityInformation.getId(entity);
        Optional<T> optionalT;
        if (StringUtils.isEmpty(entityId)) {
            String uuid = UUID.randomUUID().toString();
            //防止UUID重复
            if (findById((ID) uuid).isPresent()) {
                uuid = UUID.randomUUID().toString();
            }
            //若ID为空 则设置为UUID
            new BeanWrapperImpl(entity).setPropertyValue(entityInformation.getIdAttribute().getName(), uuid);
            //标记为新增数据
            optionalT = Optional.empty();
        } else {
            //若ID非空 则查询最新数据
            optionalT = findById(entityId);
        }
        //获取空属性并处理成null
        String[] nullProperties = getNullProperties(entity);
        //若根据ID查询结果为空
        if (!optionalT.isPresent()) {
            em.persist(entity);//新增
            return entity;
        } else {
            //1.获取最新对象
            T target = optionalT.get();
            //2.将非空属性覆盖到最新对象
            BeanUtils.copyProperties(entity, target, nullProperties);
            //3.更新非空属性
            em.merge(target);
            return entity;
        }

    }

    /**
     * 获取对象的空属性
     */
    private static String[] getNullProperties(Object src) {
        //1.获取Bean
        BeanWrapper srcBean = new BeanWrapperImpl(src);
        //2.获取Bean的属性描述
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        //3.获取Bean的空属性
        Set<String> properties = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : pds) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = srcBean.getPropertyValue(propertyName);
            if (StringUtils.isEmpty(propertyValue)) {
                srcBean.setPropertyValue(propertyName, null);
                properties.add(propertyName);
            }
        }
        return properties.toArray(new String[0]);
    }

}
