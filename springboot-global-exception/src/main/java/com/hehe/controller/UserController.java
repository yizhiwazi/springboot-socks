package com.hehe.controller;

import com.hehe.entity.R;
import com.hehe.entity.User;
import com.hehe.common.ExceptionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用全局异常处理后 Controller成功"瘦身",减少了重复代码。
 *
 * PS：有兴趣的可与参照工程对比：springboot-swagger2
 */

@RestController
@RequestMapping("/user/*")
public class UserController {

    private final static List<User> userList = new ArrayList<>();

    {
        userList.add(new User("1", "admin", "123456"));
        userList.add(new User("2", "jacks", "111111"));
    }

    @RequestMapping("/")
    public void index() throws Exception {
        ExceptionUtils.randomException();
    }


    @GetMapping("list")
    public R userlist() throws Exception {
        ExceptionUtils.randomException();
        return R.isOk().data(userList);
    }

    @PostMapping("save")
    public R save(@RequestBody User user) throws Exception {
        ExceptionUtils.randomException();
        return R.isOk().data(userList.add(user));
    }

    @PutMapping("update")
    public R update(@RequestBody User user) throws Exception {
        ExceptionUtils.randomException();
        boolean flag = false;
        if (userList.remove(user)) {
            flag = userList.add(user);
        }
        return R.isOk().data(flag);

    }

    @DeleteMapping("delete")
    public R delete(@RequestBody List<User> users) throws Exception {
        ExceptionUtils.randomException();
        return R.isOk().data(userList.removeAll(users));
    }


}
