package com.zt.bookkeeping.user.domain.user.service.login;

import com.zt.bookkeeping.user.common.base.DomainEvent;
import com.zt.bookkeeping.user.domain.jwt.JwtTokenService;
import com.zt.bookkeeping.user.domain.user.entity.LoginVO;
import com.zt.bookkeeping.user.domain.user.entity.UserAgg;
import com.zt.bookkeeping.user.domain.user.enums.LoginTypeEnum;
import com.zt.bookkeeping.user.domain.user.service.UserAggService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MobileLoginStrategy extends LoginDomainService  {


    @Override
    public LoginTypeEnum supportedType() {
        return LoginTypeEnum.MOBILE;
    }

    @Override
    public void checkParam(LoginVO loginVO) {
        // 调用RPC接口来校验验证码
    }

    @Override
    public UserAgg getUser(LoginVO loginVO) {
        // 1. 查询用户
        return userRepository.getUserByMobile(loginVO.getMobile());
    }
}
