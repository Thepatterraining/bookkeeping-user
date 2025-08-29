package com.zt.bookkeeping.user.infrastructure.repository;

import com.zt.bookkeeping.user.domain.thirdparty.entity.ThirdPartyUserAgg;
import com.zt.bookkeeping.user.domain.thirdparty.enums.PlatformTypeEnum;
import com.zt.bookkeeping.user.domain.thirdparty.repository.ThirdPartyUserRepository;
import com.zt.bookkeeping.user.infrastructure.db.ThirdPartyUserMapper;
import com.zt.bookkeeping.user.infrastructure.db.entity.ThirdPartyUserPO;
import com.zt.bookkeeping.user.infrastructure.db.ThirdPartyUserConverter;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc:
 * ------------------------------------
 * Author:zt@meituan.com
 * Date:2025/8/28
 * Time:14:21
 */
@Repository
public class ThirdPartyUserRepositoryImpl implements ThirdPartyUserRepository {

    @Resource
    private ThirdPartyUserMapper thirdPartyUserMapper;

    @Resource
    private ThirdPartyUserConverter thirdPartyUserConverter;

    @Override
    public ThirdPartyUserAgg getByPlatformAndOpenId(PlatformTypeEnum platformType, String openId) {
        ThirdPartyUserPO po = thirdPartyUserMapper.selectByPlatformAndOpenId(platformType.name(), openId);
        return thirdPartyUserConverter.toAgg(po);
    }

    @Override
    public ThirdPartyUserAgg getByPlatformAndUnionId(PlatformTypeEnum platformType, String unionId) {
        ThirdPartyUserPO po = thirdPartyUserMapper.selectByPlatformAndUnionId(platformType.name(), unionId);
        return thirdPartyUserConverter.toAgg(po);
    }

    @Override
    public ThirdPartyUserAgg getByUserIdAndPlatform(Long userId, PlatformTypeEnum platformType) {
        ThirdPartyUserPO po = thirdPartyUserMapper.selectByUserIdAndPlatform(userId, platformType.name());
        return thirdPartyUserConverter.toAgg(po);
    }

    @Override
    public List<ThirdPartyUserAgg> getByUserId(Long userId) {
        List<ThirdPartyUserPO> poList = thirdPartyUserMapper.selectByUserId(userId);
        return poList.stream()
                .map(thirdPartyUserConverter::toAgg)
                .collect(Collectors.toList());
    }

    @Override
    public ThirdPartyUserAgg insert(ThirdPartyUserAgg thirdPartyUserAgg) {
        ThirdPartyUserPO po = thirdPartyUserConverter.toPO(thirdPartyUserAgg);

        thirdPartyUserMapper.insert(po);

        // 设置ID回写到实体
        thirdPartyUserAgg.getThirdPartyUser().setId(po.getId());
        return thirdPartyUserAgg;
    }

    @Override
    public ThirdPartyUserAgg update(ThirdPartyUserAgg thirdPartyUserAgg) {
        ThirdPartyUserPO po = thirdPartyUserConverter.toPO(thirdPartyUserAgg);

        // 设置更新时间
        LocalDateTime now = LocalDateTime.now();
        po.setUpdateTime(now);

        thirdPartyUserMapper.updateById(po);

        // 更新实体的更新时间
        thirdPartyUserAgg.getThirdPartyUser().setUpdateTime(now);

        return thirdPartyUserAgg;
    }

}
