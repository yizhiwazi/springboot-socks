package com.hehe.common;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CommonService<T> {

     List<T> list();

     T get(@RequestBody T entity);

     boolean save(@RequestBody T entity);

     boolean update(@RequestBody T entity);

     boolean deleteBatch(@RequestBody List<T> entities);

}
