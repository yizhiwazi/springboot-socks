package com.hehe.repository;

import com.hehe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, String> {

    //接口方法的名称，符合约定则无需实现即可访问
    //@Query("select u from User u where u.name = ?1")
    List<User> findByUsername(String username);
}
