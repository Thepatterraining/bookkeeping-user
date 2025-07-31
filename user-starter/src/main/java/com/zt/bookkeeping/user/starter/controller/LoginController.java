package com.zt.bookkeeping.user.starter.controller;

import com.zt.bookkeeping.user.application.service.LoginApplicationService;
import com.zt.bookkeeping.user.application.dto.LoginRequest;
import com.zt.bookkeeping.user.application.dto.LoginRes;
import com.zt.bookkeeping.user.domain.user.enums.LoginTypeEnum;
import com.zt.bookkeeping.user.infrastructure.common.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Resource
    private LoginApplicationService loginApplicationService;

    @PostMapping("/password")
    public Result<LoginRes> pwdLogin(@Valid @RequestBody LoginRequest request){
        request.setLoginType(LoginTypeEnum.PASSWORD.getCode());
        LoginRes loginRes = loginApplicationService.login(request);
        return Result.success("登录成功", loginRes);
    }

    @PostMapping("/mobile")
    public Result<LoginRes> mobileLogin(@Valid @RequestBody LoginRequest request){
        request.setLoginType(LoginTypeEnum.MOBILE.getCode());
        LoginRes loginRes = loginApplicationService.login(request);
        return Result.success("登录成功", loginRes);
    }

}
