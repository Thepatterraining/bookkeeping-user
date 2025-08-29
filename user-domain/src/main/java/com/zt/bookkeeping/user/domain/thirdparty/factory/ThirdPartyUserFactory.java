package com.zt.bookkeeping.user.domain.thirdparty.factory;

import com.zt.bookkeeping.user.domain.thirdparty.entity.ThirdPartyUserAgg;
import com.zt.bookkeeping.user.domain.thirdparty.entity.ThirdPartyUserEntity;
import com.zt.bookkeeping.user.domain.thirdparty.enums.PlatformTypeEnum;
import org.springframework.stereotype.Component;

/**
 * 第三方用户工厂类
 */
@Component
public class ThirdPartyUserFactory {

    /**
     * 创建微信用户
     * @param userNo 系统用户ID
     * @param openId 微信openId
     * @param unionId 微信unionId
     * @param nickname 昵称
     * @param avatarUrl 头像URL
     * @param gender 性别
     * @param country 国家
     * @param province 省份
     * @param city 城市
     * @param language 语言
     * @return 第三方用户聚合
     */
    public ThirdPartyUserAgg createWechatUser(String userNo, String openId, String unionId,
                                             String nickname, String avatarUrl, Integer gender,
                                             String country, String province, String city, String language) {
        ThirdPartyUserEntity entity = ThirdPartyUserEntity.builder()
                .userNo(userNo)
                .platformType(PlatformTypeEnum.WECHAT)
                .openId(openId)
                .unionId(unionId)
                .nickname(nickname)
                .avatarUrl(avatarUrl)
                .gender(gender)
                .country(country)
                .province(province)
                .city(city)
                .language(language)
                .build();

        return ThirdPartyUserAgg.builder()
                .thirdPartyUser(entity)
                .build();
    }

    /**
     * 创建通用第三方用户
     * @param userNo 系统用户ID
     * @param platformType 平台类型
     * @param openId 平台用户唯一标识
     * @param nickname 昵称
     * @param avatarUrl 头像URL
     * @return 第三方用户聚合
     */
    public ThirdPartyUserAgg createThirdPartyUser(String userNo, PlatformTypeEnum platformType,
                                                String openId, String nickname, String avatarUrl) {
        ThirdPartyUserEntity entity = ThirdPartyUserEntity.builder()
                .userNo(userNo)
                .platformType(platformType)
                .openId(openId)
                .nickname(nickname)
                .avatarUrl(avatarUrl)
                .build();

        return ThirdPartyUserAgg.builder()
                .thirdPartyUser(entity)
                .build();
    }
}
