package com.zt.bookkeeping.user.domain.user.service.login;

import com.zt.bookkeeping.user.common.enums.ResultCode;
import com.zt.bookkeeping.user.domain.exception.UserLoginException;
import com.zt.bookkeeping.user.domain.user.entity.LoginVO;
import com.zt.bookkeeping.user.domain.user.entity.UserAgg;
import com.zt.bookkeeping.user.domain.user.enums.LoginTypeEnum;
import com.zt.bookkeeping.user.domain.user.respository.UserRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Desc:
 * ------------------------------------
 * Author:zt@meituan.com
 * Date:2025/7/31
 * Time:15:38
 */
@Service
@Slf4j
public abstract class LoginDomainService implements LoginStrategy {

    @Resource
    protected UserRepository userRepository;

    @Override
    abstract public LoginTypeEnum supportedType();

    abstract public void checkParam(LoginVO loginVO);

    abstract public UserAgg getUser(LoginVO loginVO);

    @Override
    public UserAgg login(LoginVO loginVO) {
        // 1. 校验参数是否正确
        checkParam(loginVO);

        // 2. 查询用户
        UserAgg userAgg = getUser(loginVO);

        // 3. 是否能登录
        canLogin(userAgg, loginVO);

        // 4. 登陆
        userAgg.login();

        return userAgg;
    }

    protected void canLogin(UserAgg userAgg, LoginVO loginVO) throws UserLoginException {
        // 0. 校验用户存在
        if (userAgg == null) {
            log.warn("用户不存在");
            throw new UserLoginException(ResultCode.USER_NOT_FOUND);
        }

        // 2. 校验用户状态
        if (!userAgg.validateStatus()) {
            log.warn("用户状态错误:{}", userAgg.getUserStatus());
            throw new UserLoginException(ResultCode.USER_STATUS_ERROR);
        }
    }
}
