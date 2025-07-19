package com.zt.bookkeeping.user.application.service;

import com.zt.bookkeeping.user.domain.jwt.JwtTokenService;
import com.zt.bookkeeping.user.domain.user.entity.UserAgg;
import com.zt.bookkeeping.user.domain.user.event.UserLoggedInEvent;
import com.zt.bookkeeping.user.domain.user.req.LoginRequest;
import com.zt.bookkeeping.user.domain.user.res.LoginRes;
import com.zt.bookkeeping.user.domain.user.service.UserAggService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UserMobileLoginApplicationService {

    @Resource
    private UserAggService userAggService;

    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Resource
    private JwtTokenService jwtTokenService;


    // 用户登录
    public LoginRes login(LoginRequest loginRequest) {
        // 校验验证码是否正确
        checkVerifyCode(loginRequest.getMobile(), loginRequest.getCode());

        // 1. 查询用户
        UserAgg userAgg = userAggService.getUserByMobile(loginRequest.getMobile());

        // 2. 调用用户领域服务校验密码和状态是否能登录
        userAggService.canLogin(userAgg);

        // 3. 发送登录成功领域事件
        eventPublisher.publishEvent(new UserLoggedInEvent(userAgg.getId(), userAgg.getUsername(), null, LocalDateTime.now()));

        // 4. 生成token
        String token = jwtTokenService.generateToken(userAgg.getUsername());

        // 5. 返回信息
        return LoginRes.builder().username(userAgg.getUsername()).token(token).userNo(userAgg.getUserNo()).mobile(userAgg.getMobile()).build();
    }

    private void checkVerifyCode(String mobile, String verifyCode) {
        // 调用RPC接口来校验验证码

    }
}
