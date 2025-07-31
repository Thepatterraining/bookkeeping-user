package com.zt.bookkeeping.user.infrastructure.listener;

import com.zt.bookkeeping.user.domain.user.event.UserLoggedInEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoginLogListener {

    @Async
    @EventListener
    public void handle(UserLoggedInEvent event) {
        log.info("写入登录日志。用户登录事件：{} ", event.toString());
        // 写入登录日志

    }
}
