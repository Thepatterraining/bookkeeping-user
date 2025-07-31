package com.zt.bookkeeping.user.domain.user.entity;

import com.zt.bookkeeping.user.domain.user.enums.LoginTypeEnum;
import lombok.Builder;
import lombok.Getter;

/**
 * Desc:
 * ------------------------------------
 * Author:zt@meituan.com
 * Date:2025/7/31
 * Time:15:40
 */
@Getter
@Builder
public class LoginVO {

    private final LoginTypeEnum type;
    private final String username;
    private final String password;
    private final String mobile;
    private final String code;
    private final String openID;
}
