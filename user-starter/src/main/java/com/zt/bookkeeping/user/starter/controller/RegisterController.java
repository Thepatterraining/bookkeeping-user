package com.zt.bookkeeping.user.starter.controller;

import com.zt.bookkeeping.user.application.service.MobileRegisterApplicationService;
import com.zt.bookkeeping.user.domain.user.req.LoginRequest;
import com.zt.bookkeeping.user.domain.user.req.MobileRegisterRequest;
import com.zt.bookkeeping.user.domain.user.res.LoginRes;
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
@RequestMapping("/user/register")
@RestController
public class RegisterController {

    @Resource
    private MobileRegisterApplicationService mobileRegisterApplicationService;

    @PostMapping("/mobile")
    public Result<Long> mobileRegister(@Valid @RequestBody MobileRegisterRequest request){
        Long registerRes = mobileRegisterApplicationService.register(request);
        return Result.success("注册成功", registerRes);
    }
}
