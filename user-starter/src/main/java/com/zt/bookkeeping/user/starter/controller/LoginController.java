package com.zt.bookkeeping.user.starter.controller;

import com.zt.bookkeeping.user.starter.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc:
 * ------------------------------------
 * Author:zt@meituan.com
 * Date:2025/7/9
 * Time:17:28
 */
@RequestMapping("/user/login")
@RestController
public class LoginController {

    @PostMapping
    public Result<Long> login(){
        // TODO: 实现登录逻辑
        Long userId = 12345L; // 示例用户ID
        return Result.success("登录成功", userId);
    }
}
