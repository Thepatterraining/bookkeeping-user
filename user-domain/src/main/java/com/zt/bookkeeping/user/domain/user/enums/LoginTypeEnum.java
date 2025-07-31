package com.zt.bookkeeping.user.domain.user.enums;

import lombok.Getter;

@Getter
public enum LoginTypeEnum {
    MOBILE(1, "手机号验证码登录"),
    PASSWORD(2, "密码登录"),
    WECHAT_OPENID(3, "微信登录");

    private final int code;
    private final String label;

    LoginTypeEnum(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public static LoginTypeEnum fromCode(int code) {
        for (LoginTypeEnum e : values()) {
            if (e.code == code) return e;
        }
        throw new IllegalArgumentException("未知登录: " + code);
    }
}
