-- 账本成员表
CREATE TABLE ledger_member (
   id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
   ledger_no  CHAR(20) NOT NULL default '' COMMENT '账本ID',
   user_no CHAR(20) NOT NULL default '' COMMENT '用户ID',
   role VARCHAR(20) DEFAULT 'MEMBER' COMMENT '角色:OWNER,MEMBER',
   join_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '加入时间',
   status TINYINT NOT NULL DEFAULT 1 COMMENT '状态:1正常,0已退出',
   `create_user`   char(20)      default ''                not null comment '创建人',
   `update_user`   char(20)      default ''                not null comment '更新人',
   `is_deleted`    bit      default b'0'                not null comment '是否删除',
   `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '账本成员表';

-- 邀请码表
CREATE TABLE invitation (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    code VARCHAR(10) NOT NULL UNIQUE default '' COMMENT '邀请码',
    ledger_no CHAR(20) NOT NULL default '' COMMENT '账本ID',
    inviter_no CHAR(20) NOT NULL default '' COMMENT '邀请人ID',
    expire_time DATETIME NOT NULL COMMENT '过期时间',
    max_use_count INT DEFAULT 1 COMMENT '最大使用次数',
    used_count INT DEFAULT 0 COMMENT '已使用次数',
    status TINYINT DEFAULT 1 COMMENT '状态:1有效,0无效',
    `create_user`   char(20)      default ''                not null comment '创建人',
    `update_user`   char(20)      default ''                not null comment '更新人',
    `is_deleted`    bit      default b'0'                not null comment '是否删除',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '邀请码表';

-- 邀请使用记录表
CREATE TABLE invitation_usage (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    invitation_id BIGINT NOT NULL COMMENT '邀请码ID',
    user_no char(20) NOT NULL COMMENT '使用者ID',
    use_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '使用时间',
    `create_user`   char(20)      default ''                not null comment '创建人',
    `update_user`   char(20)      default ''                not null comment '更新人',
    `is_deleted`    bit      default b'0'                not null comment '是否删除',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '邀请使用记录表';