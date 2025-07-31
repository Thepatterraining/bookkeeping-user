package com.zt.bookkeeping.user.application.service;

import com.zt.bookkeeping.user.common.base.DomainEvent;
import com.zt.bookkeeping.user.domain.generator.SnowFlakeGenerator;
import com.zt.bookkeeping.user.domain.user.entity.UserAgg;
import com.zt.bookkeeping.user.domain.user.event.UserRegisteredEvent;
import com.zt.bookkeeping.user.application.dto.MobileRegisterRequest;
import com.zt.bookkeeping.user.domain.user.service.UserAggService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Desc:
 * ------------------------------------
 * Author:zt@meituan.com
 * Date:2025/7/15
 * Time:11:15
 */
@Service
@Slf4j
public class MobileRegisterApplicationService {

    @Resource
    private UserAggService userAggService;

    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Resource
    private SnowFlakeGenerator snowFlakeGenerator;

    public String register(MobileRegisterRequest command) {
        // 校验验证码是否正确
        checkVerifyCode(command.getMobile(), command.getCode());

        // 2. 调用用户领域服务 判断能否注册用户
        userAggService.canRegister(command.getMobile());

        // 验证码正确 用户可以注册 生成用户聚合
        UserAgg userAgg = UserAgg.init(command.getMobile(), snowFlakeGenerator.nextId("user"));
        userAgg.register();

        // 3. 调用用户领域服务 注册用户
        userAggService.save(userAgg);

        // 4. 获取领域事件
        List<DomainEvent> events = userAgg.getDomainEvents();
        events.forEach(event -> {
            eventPublisher.publishEvent(event);
        });
        // 5. 清楚领域事件
        userAgg.clearDomainEvents();
        return userAgg.getUserNo();
    }

    private void checkVerifyCode(String mobile, String verifyCode) {
        // 调用RPC接口来校验验证码

    }
}
