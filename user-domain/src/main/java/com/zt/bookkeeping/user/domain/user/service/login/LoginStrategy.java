package com.zt.bookkeeping.user.domain.user.service.login;

import com.zt.bookkeeping.user.domain.user.entity.LoginVO;
import com.zt.bookkeeping.user.domain.user.entity.UserAgg;
import com.zt.bookkeeping.user.domain.user.enums.LoginTypeEnum;

/**
 * Desc:
 * ------------------------------------
 * Author:zt@meituan.com
 * Date:2025/7/31
 * Time:15:25
 */
public interface LoginStrategy {

    LoginTypeEnum supportedType();

    UserAgg login(LoginVO request);
}
