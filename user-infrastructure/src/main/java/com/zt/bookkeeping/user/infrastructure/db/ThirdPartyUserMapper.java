package com.zt.bookkeeping.user.infrastructure.db;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.bookkeeping.user.infrastructure.db.entity.ThirdPartyUserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 第三方用户Mapper接口
 */
@Mapper
public interface ThirdPartyUserMapper extends BaseMapper<ThirdPartyUserPO> {

    /**
     * 根据平台类型和openId查询第三方用户
     * @param platformType 平台类型
     * @param openId 平台用户唯一标识
     * @return 第三方用户PO
     */
    ThirdPartyUserPO selectByPlatformAndOpenId(@Param("platformType") String platformType, @Param("openId") String openId);

    /**
     * 根据平台类型和unionId查询第三方用户
     * @param platformType 平台类型
     * @param unionId 平台用户统一标识
     * @return 第三方用户PO
     */
    ThirdPartyUserPO selectByPlatformAndUnionId(@Param("platformType") String platformType, @Param("unionId") String unionId);

    /**
     * 根据用户ID和平台类型查询第三方用户
     * @param userId 用户ID
     * @param platformType 平台类型
     * @return 第三方用户PO
     */
    ThirdPartyUserPO selectByUserIdAndPlatform(@Param("userId") Long userId, @Param("platformType") String platformType);

    /**
     * 根据用户ID查询所有第三方用户
     * @param userId 用户ID
     * @return 第三方用户PO列表
     */
    List<ThirdPartyUserPO> selectByUserId(@Param("userId") Long userId);
}
