package com.zt.bookkeeping.user.domain.user.service.login;

import com.zt.bookkeeping.user.common.enums.ResultCode;
import com.zt.bookkeeping.user.domain.exception.UserLoginException;
import com.zt.bookkeeping.user.domain.jwt.JwtTokenService;
import com.zt.bookkeeping.user.domain.user.entity.LoginVO;
import com.zt.bookkeeping.user.domain.user.entity.UserAgg;
import com.zt.bookkeeping.user.domain.user.enums.LoginTypeEnum;
import com.zt.bookkeeping.user.domain.user.service.UserAggService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class PasswordLoginStrategy extends LoginDomainService  {

    @Override
    public LoginTypeEnum supportedType() {
        return LoginTypeEnum.PASSWORD;
    }

    @Override
    public void checkParam(LoginVO loginVO) {

    }

    @Override
    public UserAgg getUser(LoginVO loginVO) {
        // 1. 查询用户
        return userRepository.getUser(loginVO.getUsername());
    }

    @Override
    protected void canLogin(UserAgg userAgg, LoginVO loginVO) {
        super.canLogin(userAgg, loginVO);
        // 1. 校验用户密码
        if (!userAgg.validatePassword(loginVO.getPassword())) {
            log.warn("用户密码错误,用户输入的密码:{}, 用户的密码:{}",  loginVO.getPassword(), userAgg.getPassword());
            throw new UserLoginException(ResultCode.USER_PASSWORD_ERROR);
        }
    }
}
