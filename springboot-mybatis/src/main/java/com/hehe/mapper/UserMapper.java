package com.hehe.mapper;

import com.hehe.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("select * from t_user where 1=1")
    List<User> list();

    @Select("select * from t_user where username like #{username}")
    List<User> findByUsername(String username);

    @Select("select * from t_user where user_id like #{userId}")
    User getOne(String userId);

    @Delete("delete from t_user where user_id like #{userId}")
    int delete(String userId);

}
