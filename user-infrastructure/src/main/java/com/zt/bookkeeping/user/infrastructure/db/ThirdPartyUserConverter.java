package com.zt.bookkeeping.user.infrastructure.db;

import com.zt.bookkeeping.user.domain.thirdparty.entity.ThirdPartyUserAgg;
import com.zt.bookkeeping.user.domain.thirdparty.entity.ThirdPartyUserEntity;
import com.zt.bookkeeping.user.domain.thirdparty.enums.PlatformTypeEnum;
import com.zt.bookkeeping.user.infrastructure.db.entity.ThirdPartyUserPO;
import org.springframework.stereotype.Component;

/**
 * 第三方用户转换器
 */
@Component
public class ThirdPartyUserConverter {

    /**
     * PO转实体
     * @param po 第三方用户PO
     * @return 第三方用户聚合
     */
    public ThirdPartyUserAgg toAgg(ThirdPartyUserPO po) {
        if (po == null) {
            return null;
        }

        ThirdPartyUserEntity entity = ThirdPartyUserEntity.builder()
                .id(po.getId())
                .userNo(po.getUserNo())
                .platformType(PlatformTypeEnum.fromCode(po.getPlatformType()))
                .openId(po.getOpenId())
                .unionId(po.getUnionId())
                .nickname(po.getNickname())
                .avatarUrl(po.getAvatarUrl())
                .gender(po.getGender())
                .country(po.getCountry())
                .province(po.getProvince())
                .city(po.getCity())
                .language(po.getLanguage())
                .extraInfo(po.getExtraInfo())
                .createTime(po.getCreateTime())
                .updateTime(po.getUpdateTime())
                .build();

        return ThirdPartyUserAgg.builder()
                .thirdPartyUser(entity)
                .build();
    }

    /**
     * 实体转PO
     * @param agg 第三方用户聚合
     * @return 第三方用户PO
     */
    public ThirdPartyUserPO toPO(ThirdPartyUserAgg agg) {
        if (agg == null || agg.getThirdPartyUser() == null) {
            return null;
        }

        ThirdPartyUserEntity entity = agg.getThirdPartyUser();
        ThirdPartyUserPO po = new ThirdPartyUserPO();

        po.setId(entity.getId());
        po.setUserNo(entity.getUserNo());
        po.setPlatformType(entity.getPlatformType().getCode());
        po.setOpenId(entity.getOpenId());
        po.setUnionId(entity.getUnionId());
        po.setNickname(entity.getNickname());
        po.setAvatarUrl(entity.getAvatarUrl());
        po.setGender(entity.getGender());
        po.setCountry(entity.getCountry());
        po.setProvince(entity.getProvince());
        po.setCity(entity.getCity());
        po.setLanguage(entity.getLanguage());
        po.setExtraInfo(entity.getExtraInfo());
        po.setCreateTime(entity.getCreateTime());
        po.setUpdateTime(entity.getUpdateTime());

        return po;
    }
}
