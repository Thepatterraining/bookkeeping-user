package com.zt.bookkeeping.user.domain.thirdparty.entity;

import com.zt.bookkeeping.user.domain.thirdparty.enums.PlatformTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 第三方用户实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThirdPartyUserEntity {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 关联的系统用户ID
     */
    private String userNo;

    /**
     * 平台类型
     */
    private PlatformTypeEnum platformType;

    /**
     * 平台用户唯一标识
     */
    private String openId;

    /**
     * 平台用户统一标识（如微信unionId）
     */
    private String unionId;

    /**
     * 第三方平台昵称
     */
    private String nickname;

    /**
     * 头像URL
     */
    private String avatarUrl;

    /**
     * 性别：0-未知，1-男，2-女
     */
    private Integer gender;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 语言
     */
    private String language;

    /**
     * 额外信息（JSON格式）
     */
    private String extraInfo;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
