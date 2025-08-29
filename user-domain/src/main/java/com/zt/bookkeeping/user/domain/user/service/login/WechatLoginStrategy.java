package com.zt.bookkeeping.user.domain.user.service.login;

import com.zt.bookkeeping.user.common.enums.ResultCode;
import com.zt.bookkeeping.user.domain.exception.UserLoginException;
import com.zt.bookkeeping.user.domain.gateway.wechat.WechatUserService;
import com.zt.bookkeeping.user.domain.gateway.wechat.model.WechatUserInfoBO;
import com.zt.bookkeeping.user.domain.thirdparty.entity.ThirdPartyUserAgg;
import com.zt.bookkeeping.user.domain.thirdparty.enums.PlatformTypeEnum;
import com.zt.bookkeeping.user.domain.thirdparty.service.ThirdPartyUserDomainService;
import com.zt.bookkeeping.user.domain.user.entity.LoginVO;
import com.zt.bookkeeping.user.domain.user.entity.UserAgg;
import com.zt.bookkeeping.user.domain.user.enums.LoginTypeEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class WechatLoginStrategy extends LoginDomainService {

    @Resource
    private WechatUserService wechatUserService;

    @Resource
    private ThirdPartyUserDomainService thirdPartyUserDomainService;

    @Override
    public LoginTypeEnum supportedType() {
        return LoginTypeEnum.WECHAT_OPENID;
    }

    @Override
    public void checkParam(LoginVO loginVO) {
        if (!StringUtils.hasText(loginVO.getCode())) {
            log.warn("微信登录code不能为空");
            throw new UserLoginException(ResultCode.BAD_REQUEST);
        }
    }

    @Override
    public UserAgg getUser(LoginVO loginVO) {
        // 1. 通过code获取openId和unionId
        WechatUserInfoBO wechatInfo = wechatUserService.getWechatInfo(loginVO.getCode());
        String openId = wechatInfo.getOpenid();
        String unionId = wechatInfo.getUnionid();

        if (!StringUtils.hasText(openId)) {
            log.warn("获取微信openId失败");
            throw new UserLoginException(ResultCode.INTERNAL_SERVER_ERROR);
        }

        // 2. 查询微信用户
        ThirdPartyUserAgg thirdPartyUserAgg = thirdPartyUserDomainService.getUserByPlatformAndOpenId(PlatformTypeEnum.WECHAT, openId);
        if (thirdPartyUserAgg == null) {
            log.warn("微信用户不存在，需要自动注册微信用户，openId: {}", openId);
            // 2.1 微信用户不存在，需要自动注册微信用户
//            thirdPartyUserAgg = thirdPartyUserDomainService.register(PlatformTypeEnum.WECHAT, openId, unionId);
        }
        // 2. 查询系统用户
        String userNo = thirdPartyUserAgg.getUserNo();
        if (userNo == null) {
            return null;
        }

        return userRepository.load(userNo);

        // 3. 如果用户不存在，可以在这里实现自动注册逻辑
//        if (userAgg == null) {
//            log.info("微信用户未绑定系统账号，openId: {}", openId);
//            // 可以在这里实现自动注册逻辑，或者抛出异常要求用户先注册
//            throw new UserLoginException(ResultCode.USER_NOT_FOUND);
//        }
//
//        return userAgg;
//        return null;
    }
}
