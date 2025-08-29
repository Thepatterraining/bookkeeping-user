package com.zt.bookkeeping.user.domain.thirdparty.repository;

import com.zt.bookkeeping.user.domain.thirdparty.entity.ThirdPartyUserAgg;
import com.zt.bookkeeping.user.domain.thirdparty.enums.PlatformTypeEnum;

import java.util.List;

/**
 * 第三方用户仓储接口
 */
public interface ThirdPartyUserRepository {

    /**
     * 根据平台类型和openId查询第三方用户
     * @param platformType 平台类型
     * @param openId 平台用户唯一标识
     * @return 第三方用户聚合
     */
    ThirdPartyUserAgg getByPlatformAndOpenId(PlatformTypeEnum platformType, String openId);

    /**
     * 根据平台类型和unionId查询第三方用户
     * @param platformType 平台类型
     * @param unionId 平台用户统一标识
     * @return 第三方用户聚合
     */
    ThirdPartyUserAgg getByPlatformAndUnionId(PlatformTypeEnum platformType, String unionId);

    /**
     * 根据用户ID和平台类型查询第三方用户
     * @param userId 用户ID
     * @param platformType 平台类型
     * @return 第三方用户聚合
     */
    ThirdPartyUserAgg getByUserIdAndPlatform(Long userId, PlatformTypeEnum platformType);

    /**
     * 根据用户ID查询所有第三方用户
     * @param userId 用户ID
     * @return 第三方用户聚合列表
     */
    List<ThirdPartyUserAgg> getByUserId(Long userId);

    /**
     * 保存第三方用户
     * @param thirdPartyUserAgg 第三方用户聚合
     * @return 保存后的第三方用户聚合
     */
    ThirdPartyUserAgg insert(ThirdPartyUserAgg thirdPartyUserAgg);

    /**
     * 更新第三方用户
     * @param thirdPartyUserAgg 第三方用户聚合
     * @return 更新后的第三方用户聚合
     */
    ThirdPartyUserAgg update(ThirdPartyUserAgg thirdPartyUserAgg);
}
