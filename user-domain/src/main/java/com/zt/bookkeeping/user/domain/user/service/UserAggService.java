package com.zt.bookkeeping.user.domain.user.service;

import com.zt.bookkeeping.user.domain.user.entity.UserAgg;
import org.springframework.stereotype.Service;

@Service
public class UserAggService {

    public boolean canLogin(UserAgg userAgg, String inputPassword) {
        // 1. 校验用户密码
        if (!userAgg.validatePassword(inputPassword)) {
            return false;
        }

        // 2. 校验用户状态
        if (!userAgg.validateStatus()) {
            return false;
        }
        return true;
    }

}
