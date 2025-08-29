package com.zt.bookkeeping.user.domain.thirdparty.service;

import com.zt.bookkeeping.user.domain.thirdparty.entity.ThirdPartyUserAgg;
import com.zt.bookkeeping.user.domain.thirdparty.enums.PlatformTypeEnum;
import com.zt.bookkeeping.user.domain.thirdparty.factory.ThirdPartyUserFactory;
import com.zt.bookkeeping.user.domain.thirdparty.repository.ThirdPartyUserRepository;
import com.zt.bookkeeping.user.domain.user.entity.UserAgg;
import com.zt.bookkeeping.user.domain.user.respository.UserRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 第三方用户领域服务
 */
@Service
@Slf4j
public class ThirdPartyUserDomainService {

    @Resource
    private ThirdPartyUserRepository thirdPartyUserRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private ThirdPartyUserFactory thirdPartyUserFactory;

    /**
     * 根据平台类型和openId查询用户
     * @param platformType 平台类型
     * @param openId 平台用户唯一标识
     * @return 系统用户聚合
     */
    public ThirdPartyUserAgg getUserByPlatformAndOpenId(PlatformTypeEnum platformType, String openId) {
        // 1. 查询第三方用户
        ThirdPartyUserAgg thirdPartyUserAgg = thirdPartyUserRepository.getByPlatformAndOpenId(platformType, openId);
        if (thirdPartyUserAgg == null) {
            return null;
        }
        return thirdPartyUserAgg;
    }

//    /**
//     * 绑定第三方用户到系统用户
//     * @param userId 系统用户ID
//     * @param platformType 平台类型
//     * @param openId 平台用户唯一标识
//     * @param unionId 平台用户统一标识
//     * @param nickname 昵称
//     * @param avatarUrl 头像URL
//     * @param gender 性别
//     * @param country 国家
//     * @param province 省份
//     * @param city 城市
//     * @param language 语言
//     * @return 第三方用户聚合
//     */
//    @Transactional(rollbackFor = Exception.class)
//    public ThirdPartyUserAgg bindThirdPartyUser(String userNo, PlatformTypeEnum platformType,
//                                              String openId, String unionId, String nickname,
//                                              String avatarUrl, Integer gender, String country,
//                                              String province, String city, String language) {
//        // 1. 查询是否已存在绑定关系
//        ThirdPartyUserAgg existingUser = thirdPartyUserRepository.getByPlatformAndOpenId(platformType, openId);
//        if (existingUser != null) {
//            // 如果已存在且userId不同，则更新绑定关系
//            if (!userNo.equals(existingUser.getUserNo())) {
//                existingUser.bindUser(userNo);
//                existingUser.updateUserInfo(nickname, avatarUrl, gender, country, province, city, language);
//                return thirdPartyUserRepository.update(existingUser);
//            }
//            // 如果已存在且userId相同，则更新用户信息
//            existingUser.updateUserInfo(nickname, avatarUrl, gender, country, province, city, language);
//            return thirdPartyUserRepository.update(existingUser);
//        }
//
//        // 2. 创建新的第三方用户
//        ThirdPartyUserAgg newUser;
//        if (platformType == PlatformTypeEnum.WECHAT) {
//            newUser = thirdPartyUserFactory.createWechatUser(userNo, openId, unionId, nickname,
//                    avatarUrl, gender, country, province, city, language);
//        } else {
//            newUser = thirdPartyUserFactory.createThirdPartyUser(userNo, platformType, openId, nickname, avatarUrl);
//        }
//
//        // 3. 保存第三方用户
//        return thirdPartyUserRepository.insert(newUser);
//    }

    /**
     * 解绑第三方用户
     * @param userId 系统用户ID
     * @param platformType 平台类型
     * @return 是否成功
     */
//    @Transactional(rollbackFor = Exception.class)
//    public boolean unbindThirdPartyUser(Long userId, PlatformTypeEnum platformType) {
//        ThirdPartyUserAgg thirdPartyUserAgg = thirdPartyUserRepository.getByUserIdAndPlatform(userId, platformType);
//        if (thirdPartyUserAgg == null) {
//            return false;
//        }
//
//        // 这里可以根据业务需求选择删除或者将userId置为null
//        thirdPartyUserAgg.bindUser(null);
//        thirdPartyUserRepository.update(thirdPartyUserAgg);
//        return true;
//    }
}
