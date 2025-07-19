package com.zt.bookkeeping.user.application.service;

import com.zt.bookkeeping.user.common.enums.ResultCode;
import com.zt.bookkeeping.user.domain.exception.UserLoginException;
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
public class UserLoginApplicationService {

    @Resource
    private UserAggService userAggService;

    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Resource
    private JwtTokenService jwtTokenService;


    // 用户登录
    public LoginRes login(LoginRequest loginRequest) {
        // 1. 调用用户领域服务 查询用户信息
        UserAgg userAgg = userAggService.getUserByUserName(loginRequest.getUsername());

        // 2. 调用用户领域服务校验密码和状态是否能登录
        // 1. 校验用户密码
        if (!userAgg.validatePassword(loginRequest.getPassword())) {
            log.warn("用户密码错误,用户输入的密码:{}, 用户的密码:{}",  loginRequest.getPassword(), userAgg.getPassword());
            throw new UserLoginException(ResultCode.USER_PASSWORD_ERROR);
        }
        userAggService.canLogin(userAgg);

        // 3. 发送登录成功领域事件
        eventPublisher.publishEvent(new UserLoggedInEvent(userAgg.getId(), userAgg.getUsername(), null, LocalDateTime.now()));

        // 4. 生成token
        String token = jwtTokenService.generateToken(userAgg.getUsername());

        // 5. 返回信息
        return LoginRes.builder().username(userAgg.getUsername()).token(token).userNo(userAgg.getUserNo()).mobile(userAgg.getMobile()).build();
    }
}
