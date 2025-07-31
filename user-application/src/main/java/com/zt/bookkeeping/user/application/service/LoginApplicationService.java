package com.zt.bookkeeping.user.application.service;

import com.zt.bookkeeping.user.application.dto.LoginRequest;
import com.zt.bookkeeping.user.application.dto.LoginRes;
import com.zt.bookkeeping.user.common.base.DomainEvent;
import com.zt.bookkeeping.user.domain.jwt.JwtTokenService;
import com.zt.bookkeeping.user.domain.user.entity.LoginVO;
import com.zt.bookkeeping.user.domain.user.entity.UserAgg;
import com.zt.bookkeeping.user.domain.user.enums.LoginTypeEnum;
import com.zt.bookkeeping.user.domain.user.service.login.LoginDomainService;
import com.zt.bookkeeping.user.domain.user.service.login.LoginStrategy;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Desc:
 * ------------------------------------
 * Author:zt@meituan.com
 * Date:2025/7/31
 * Time:15:22
 */
@Service
@Slf4j
public class LoginApplicationService {

    @Resource
    private List<LoginStrategy> loginStrategies;

    private final Map<LoginTypeEnum, LoginStrategy> loginStrategyMap = new HashMap<>();

    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Resource
    private JwtTokenService jwtTokenService;

    @PostConstruct
    public void init() {
        loginStrategies.forEach(loginStrategy -> loginStrategyMap.put(loginStrategy.supportedType(), loginStrategy));
    }

    public LoginRes login(LoginRequest request) {
        // 1. 解析登录类型
        LoginTypeEnum loginType = LoginTypeEnum.fromCode(request.getLoginType());

        // 2. 执行登录
        LoginVO loginVO = LoginVO.builder()
                .type(loginType)
                .mobile(request.getMobile())
                .code(request.getCode())
                .password(request.getPassword())
                .username(request.getUsername())
                .build();
        LoginStrategy loginStrategy = loginStrategyMap.get(loginType);
        UserAgg userAgg = loginStrategy.login(loginVO);

        // 3. 发送登录成功领域事件
        List<DomainEvent> events = userAgg.getDomainEvents();
        events.forEach(event -> {
            eventPublisher.publishEvent(event);
        });
        // 5. 清除领域事件
        userAgg.clearDomainEvents();

        // 4. 生成token
        String token = jwtTokenService.generateToken(userAgg.getUsername());

        // 5. 返回信息
        return LoginRes.builder().username(userAgg.getUsername()).token(token).userNo(userAgg.getUserNo()).mobile(userAgg.getMobile()).build();
    }
}
