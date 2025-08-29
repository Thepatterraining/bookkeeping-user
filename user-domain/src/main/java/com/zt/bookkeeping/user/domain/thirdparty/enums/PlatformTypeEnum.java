package com.zt.bookkeeping.user.domain.thirdparty.enums;

import lombok.Getter;

/**
 * 第三方平台类型枚举
 */
@Getter
public enum PlatformTypeEnum {
    WECHAT(1, "微信"),
//    ALIPAY(2, "支付宝"),
//    QQ(3, "QQ"),
//    WEIBO(4, "微博"),
//    GOOGLE(5, "谷歌"),
//    FACEBOOK(6, "脸书"),
//    TWITTER(7, "推特"),
    ;

    private final int code;
    private final String label;

    PlatformTypeEnum(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public static PlatformTypeEnum fromCode(int code) {
        for (PlatformTypeEnum e : values()) {
            if (e.code == code) return e;
        }
        throw new IllegalArgumentException("未知平台类型: " + code);
    }
}
