package com.zt.bookkeeping.user.starter.controller;

import com.zt.bookkeeping.user.application.service.UserLoginApplicationService;
import com.zt.bookkeeping.user.domain.user.req.LoginRequest;
import com.zt.bookkeeping.user.domain.user.res.LoginRes;
import com.zt.bookkeeping.user.infrastructure.common.Result;
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

    private UserLoginApplicationService userLoginApplicationService;

    @PostMapping
    public Result<LoginRes> login(@Valid @RequestBody LoginRequest request){
        LoginRes loginRes = userLoginApplicationService.login(request);
        return Result.success("登录成功", loginRes);
    }

//    @PostMapping("/login")
//    public ResponseMessage<LoginRes> login(@Valid @RequestBody LoginRequest request) {
//        log.info("登陆:{}", request);
//        AdminUser user = loginService.login(request.username(), request.password());
//        String token = JwtUtil.generateToken(user.getMobile());
//        LoginRes res = new LoginRes();
//        res.setToken(token);
//        res.setId(user.getId());
//        res.setName(user.getName());
//        res.setMobile(user.getMobile());
//        res.setAvatar(user.getAvatar());
//        res.setUserNo(user.getUserNo());
//        res.setEmail(user.getEmail());
//        res.setCreateTime(user.getCreateTime());
//        res.setUpdateTime(user.getUpdateTime());
//        return ResponseMessage.success(res);
//    }
}
