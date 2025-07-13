package com.zt.bookkeeping.user.application.service;

import com.zt.bookkeeping.user.domain.user.entity.UserAgg;
import com.zt.bookkeeping.user.domain.user.event.UserLoggedInEvent;
import com.zt.bookkeeping.user.domain.user.req.LoginRequest;
import com.zt.bookkeeping.user.domain.user.res.LoginRes;
import com.zt.bookkeeping.user.domain.user.respository.UserRepository;
import com.zt.bookkeeping.user.domain.user.service.UserAggService;
import com.zt.bookkeeping.user.domain.util.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserLoginApplicationService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserAggService userAggService;

    @Resource
    private ApplicationEventPublisher eventPublisher;


    // 用户登录
    public LoginRes login(LoginRequest loginRequest) {
        // 1. 调用用户领域服务 查询用户信息
        UserAgg userAgg = userRepository.getUser(loginRequest.getUsername());

        // 2. 调用用户领域服务校验密码和状态是否能登录
        if (!userAggService.canLogin(userAgg, loginRequest.getPassword())) {
            // 抛出异常

        }

        // 3. 发送登录成功领域事件
        eventPublisher.publishEvent(new UserLoggedInEvent(userAgg.getId(), userAgg.getUsername(), null, LocalDateTime.now()));

        // 4. 生成token
        String token = JwtUtil.generateToken(userAgg.getUsername());

        // 5. 返回信息
        return LoginRes.builder().username(userAgg.getUsername()).token(token).userNo(userAgg.getUserNo()).mobile(userAgg.getMobile()).build();
    }
}
