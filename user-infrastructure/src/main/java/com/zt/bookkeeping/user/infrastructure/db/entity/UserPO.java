package com.zt.bookkeeping.user.infrastructure.db.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zt.bookkeeping.user.common.base.AbstractPO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@TableName("user")
@NoArgsConstructor
public class UserPO extends AbstractPO {

    private String userNo;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private Integer gender;
    private Integer age;
    private Integer userStatus;
    private Integer userType;
}
