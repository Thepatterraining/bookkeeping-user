package com.zt.bookkeeping.user.domain.gateway.wechat;

import com.zt.bookkeeping.user.domain.gateway.wechat.model.WechatUserInfoBO;

/**
 * 微信用户服务接口
 */
public interface WechatUserService {

    /**
     * 通过code获取微信用户信息
     * @param code 微信授权code
     * @return 微信用户信息
     */
    WechatUserInfoBO getWechatInfo(String code);
}
