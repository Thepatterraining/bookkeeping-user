package com.zt.bookkeeping.user.infrastructure.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zt.bookkeeping.user.common.base.AbstractPO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 第三方用户PO对象
 */
@Data
@SuperBuilder
@TableName("third_party_user")
@NoArgsConstructor
public class ThirdPartyUserPO extends AbstractPO {

    /**
     * 关联的系统用户ID
     */
    @TableField("user_no")
    private String userNo;

    /**
     * 平台类型：WECHAT/ALIPAY/GOOGLE等
     */
    @TableField("platform_type")
    private Integer platformType;

    /**
     * 平台用户唯一标识
     */
    @TableField("open_id")
    private String openId;

    /**
     * 平台用户统一标识（如微信unionId）
     */
    @TableField("union_id")
    private String unionId;

    /**
     * 第三方平台昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 头像URL
     */
    @TableField("avatar_url")
    private String avatarUrl;

    /**
     * 性别：0-未知，1-男，2-女
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 国家
     */
    @TableField("country")
    private String country;

    /**
     * 省份
     */
    @TableField("province")
    private String province;

    /**
     * 城市
     */
    @TableField("city")
    private String city;

    /**
     * 语言
     */
    @TableField("language")
    private String language;

    /**
     * 额外信息（JSON格式）
     */
    @TableField("extra_info")
    private String extraInfo;
}
