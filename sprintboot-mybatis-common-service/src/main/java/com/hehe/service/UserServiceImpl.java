package com.hehe.service;


import com.hehe.common.CommonService;
import com.hehe.common.CommonServiceImpl;
import com.hehe.domain.User;
import org.springframework.stereotype.Service;

/**
 *  业务ServiceImpl类继承通用ServiceImpl实现数据查询功能
 */

@Service
public class UserServiceImpl extends CommonServiceImpl<User> implements UserService {

}
