package com.zt.bookkeeping.user.domain.thirdparty.entity;

import com.zt.bookkeeping.user.domain.thirdparty.enums.PlatformTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 第三方用户聚合根
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThirdPartyUserAgg {
    /**
     * 第三方用户实体
     */
    private ThirdPartyUserEntity thirdPartyUser;

    /**
     * 根据平台类型和openId查找用户
     * @param platformType 平台类型
     * @param openId 平台用户唯一标识
     * @return 是否匹配
     */
    public boolean matchPlatformAndOpenId(PlatformTypeEnum platformType, String openId) {
        return thirdPartyUser != null
                && thirdPartyUser.getPlatformType() == platformType
                && openId.equals(thirdPartyUser.getOpenId());
    }

    /**
     * 更新用户信息
     * @param nickname 昵称
     * @param avatarUrl 头像URL
     * @param gender 性别
     * @param country 国家
     * @param province 省份
     * @param city 城市
     * @param language 语言
     */
    public void updateUserInfo(String nickname, String avatarUrl, Integer gender,
                              String country, String province, String city, String language) {
        if (thirdPartyUser != null) {
            thirdPartyUser.setNickname(nickname);
            thirdPartyUser.setAvatarUrl(avatarUrl);
            thirdPartyUser.setGender(gender);
            thirdPartyUser.setCountry(country);
            thirdPartyUser.setProvince(province);
            thirdPartyUser.setCity(city);
            thirdPartyUser.setLanguage(language);
        }
    }

    /**
     * 关联系统用户
     * @param userNo 系统用户ID
     */
    public void bindUser(String userNo) {
        if (thirdPartyUser != null) {
            thirdPartyUser.setUserNo(userNo);
        }
    }

    /**
     * 获取用户ID
     * @return 用户ID
     */
    public String getUserNo() {
        return thirdPartyUser != null ? thirdPartyUser.getUserNo() : null;
    }

    /**
     * 获取OpenID
     * @return OpenID
     */
    public String getOpenId() {
        return thirdPartyUser != null ? thirdPartyUser.getOpenId() : null;
    }

    /**
     * 获取UnionID
     * @return UnionID
     */
    public String getUnionId() {
        return thirdPartyUser != null ? thirdPartyUser.getUnionId() : null;
    }
}
