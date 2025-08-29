-- 创建第三方用户表
CREATE TABLE IF NOT EXISTS `third_party_user` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    `user_no` BIGINT COMMENT '关联的系统用户ID',
    `platform_type` VARCHAR(20) NOT NULL COMMENT '平台类型：WECHAT/ALIPAY/GOOGLE等',
    `open_id` VARCHAR(128) COMMENT '平台用户唯一标识',
    `union_id` VARCHAR(128) COMMENT '平台用户统一标识（如微信unionId）',
    `nickname` VARCHAR(64) COMMENT '第三方平台昵称',
    `avatar_url` VARCHAR(512) COMMENT '头像URL',
    `gender` TINYINT COMMENT '性别：0-未知，1-男，2-女',
    `country` VARCHAR(64) COMMENT '国家',
    `province` VARCHAR(64) COMMENT '省份',
    `city` VARCHAR(64) COMMENT '城市',
    `language` VARCHAR(32) COMMENT '语言',
    `extra_info` TEXT COMMENT '额外信息（JSON格式）',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_platform_openid` (`platform_type`, `open_id`),
    UNIQUE KEY `uk_platform_user` (`platform_type`, `user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='第三方用户信息表';

-- 创建第三方用户表
CREATE TABLE IF NOT EXISTS `file_storage` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    `user_no` BIGINT CHAR(20) '关联的系统用户ID',
    `file_hash` VARCHAR(100) NOT NULL COMMENT '文件hash',
    `file_name` VARCHAR(255) COMMENT '上传后的文件名称',
    `original_file_name` VARCHAR(255) COMMENT '文件原名称',
    `file_url` VARCHAR(500) COMMENT 'url',
    `file_type` VARCHAR(30) COMMENT '文件类型',
    `file_size` int(11) COMMENT '文件大小',
    `bucket_name` VARCHAR(64) COMMENT 'bucket name',
    `storage_type` tinyint COMMENT '存储类型',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件存储表';
