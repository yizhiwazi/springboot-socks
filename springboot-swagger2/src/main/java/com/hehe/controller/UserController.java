package com.hehe.controller;

import com.hehe.entity.R;
import com.hehe.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Api("用户管理")
@RestController
@RequestMapping("/user/*")
public class UserController {

    private final static List<User> userList = new ArrayList<>();

    {
        userList.add(new User("1", "admin", "123456"));
        userList.add(new User("2", "jacks", "111111"));
    }

    @ApiOperation("获取列表")
    @GetMapping("list")
    public R userlist() {
        try {
            if (Math.random() > 0.9) {
                throw new NullPointerException();
            }
            return R.isOk().data(userList);
        } catch (NullPointerException e) {
            return R.isFail(e);
        }

    }

    @ApiOperation("新增用户")
    @ApiImplicitParam(name = "user",value = "用户信息",dataType = "User")
    @PostMapping("save")
    public R save(@RequestBody User user) {
        try {
            return R.isOk().data(userList.add(user));
        } catch (Exception e) {
            return R.isFail(e);
        }
    }

    @ApiOperation("更新用户")
    @PutMapping("update")
    public R update(@RequestBody User user) {
        boolean flag=false;
        try {
            if (userList.remove(user)) {
                flag=userList.add(user);
            }
            return R.isOk().data(flag);
        } catch (Exception e) {
            return R.isFail(e);
        }
    }

    @ApiOperation("批量删除")
    @DeleteMapping("delete")
    public R delete(@RequestBody List<User> users) {
        try {
            return R.isOk().data(userList.removeAll(users));
        } catch (Exception e) {
            return R.isFail(e);
        }
    }


}
