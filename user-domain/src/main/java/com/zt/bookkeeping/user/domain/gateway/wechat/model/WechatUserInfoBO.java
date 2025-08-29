package com.zt.bookkeeping.user.domain.gateway.wechat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信用户信息BO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WechatUserInfoBO {
    /**
     * 微信openId
     */
    private String openid;

    /**
     * 微信unionId
     */
    private String unionid;

    /**
     * 会话密钥
     */
    private String sessionKey;

    /**
     * 错误码
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;
}
