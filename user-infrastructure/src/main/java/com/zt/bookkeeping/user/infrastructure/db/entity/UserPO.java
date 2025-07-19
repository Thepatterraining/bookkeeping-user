package com.zt.bookkeeping.user.infrastructure.db.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@TableName("user")
public class UserPO {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userNo;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private Integer gender;
    private Integer age;
    private Integer userStatus;
    private Integer userType;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
