package com.hehe.service;

import com.hehe.pojo.User;
import com.hehe.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService {

    @SuppressWarnings("all")
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public List<User> findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User get(String userId) {
        return userMapper.getOne(userId);
    }

    @Override
    public int delete(String userId) {
        return userMapper.delete(userId);
    }
}
