package com.zt.bookkeeping.user.common.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Data
@NoArgsConstructor
public abstract class AbstractPO {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String createUser;
    private String updateUser;
    private Boolean isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
