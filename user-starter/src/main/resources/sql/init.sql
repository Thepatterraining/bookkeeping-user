create table user
(
    id          int auto_increment comment 'id'
        primary key,
    user_no     char(20)     default ''                not null comment '用户编号',
    username    varchar(50)  default ''                not null comment '用户名称',
    password    varchar(255) default ''                not null comment '密码',
    mobile      varchar(20)  default ''                not null comment '手机号',
    email       varchar(255) default ''                not null comment '邮箱',
    avatar      varchar(255) default ''                not null comment '头像',
    gender      tinyint      default 0                 not null comment ' 性别 0 未知 1 男 2 女',
    age         tinyint      default 0                 not null comment '年龄',
    user_status tinyint      default 0                 not null comment '用户状态',
    user_type   tinyint      default 0                 not null comment '用户类型',
    create_user   char(20)      default ''                not null comment '创建人',
    update_user   char(20)      default ''                not null comment '更新人',
    is_deleted    bit      default b'0'                not null comment '是否删除',
    create_time datetime     default CURRENT_TIMESTAMP null,
    update_time datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

create table ledger
(
    id          int auto_increment comment 'id'
        primary key,
    ledger_no     char(20)     default ''                not null comment '账本编号',
    ledger_name    varchar(50)  default ''                not null comment '账本名称',
    owner_no    char(20) default ''                not null comment '所属人编号',
    ledger_image    varchar(255) default ''                not null comment '账本封面',
    ledger_desc    varchar(255) default ''                not null comment '账本描述',
    ledger_status tinyint      default 0                 not null comment '账本状态',
    create_user   char(20)      default ''                not null comment '创建人',
    update_user   char(20)      default ''                not null comment '更新人',
    is_deleted    bit      default b'0'                not null comment '是否删除',
    create_time datetime     default CURRENT_TIMESTAMP null,
    update_time datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

create table ledger_budget
(
    id          int auto_increment comment 'id'
        primary key,
    ledger_no     char(20)     default ''                not null comment '账本编号',
    budget_amount    int(11)  default 0                not null comment '预算金额',
    used_amount     int(11)   default 0                not null comment '已使用金额',
    remained_amount int(11)   default 0                not null comment '剩余金额',
    budget_date    date                                          comment '预算日期',
    create_user   char(20)      default ''                not null comment '创建人',
    update_user   char(20)      default ''                not null comment '更新人',
    is_deleted    bit      default b'0'                not null comment '是否删除',
    create_time datetime     default CURRENT_TIMESTAMP null,
    update_time datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

create table event
(
    id          int auto_increment comment 'id'
        primary key,
    event_id     char(20)     default ''                not null comment '事件编号',
    event_type    varchar(20)  default ''                not null comment '事件类型',
    event_data     json                                              comment '事件数据',
    event_time    datetime                                          comment '事件发生时间',
    event_status tinyint      default 0                 not null comment '事件状态 0 待处理 1 处理中 2 已完成',
    create_user   char(20)      default ''                not null comment '创建人',
    update_user   char(20)      default ''                not null comment '更新人',
    is_deleted    bit      default b'0'                not null comment '是否删除',
    create_time datetime     default CURRENT_TIMESTAMP null,
    update_time datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

create table transaction_statement
(
    id          int auto_increment comment 'id'
        primary key,
    transaction_statement_no     char(20)     default ''                not null comment '交易流水编号',
    ledger_no    char(20)  default ''                not null comment '账本编号',
    amount     int(11)   default 0                not null comment '交易金额',
    transaction_type int(11)   default 0                not null comment '交易类型 1 收入 2 支出',
    transaction_time    datetime                                          comment '交易时间',
    transaction_desc    varchar(255) default ''                not null comment '交易描述',
    transaction_status tinyint      default 0                 not null comment '交易状态',
    category_no    char(20) default ''                not null comment '分类编号',
    category_name    char(20) default ''                not null comment '分类名称',
    category_type    char(20) default ''                not null comment '分类类型 1 系统 2 用户',
    category_icon varchar(255)   default ''                not null comment '分类图标',
    create_user   char(20)      default ''                not null comment '创建人',
    update_user   char(20)      default ''                not null comment '更新人',
    is_deleted    bit      default b'0'                not null comment '是否删除',
    create_time datetime     default CURRENT_TIMESTAMP null,
    update_time datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

create table sys_category
(
    id          int auto_increment comment 'id'
        primary key,
    category_no     char(20)     default ''                not null comment '分类编号',
    category_name    varchar(20)  default ''                not null comment '分类名称',
    category_desc     varchar(255)   default ''                not null comment '分类描述',
    category_icon varchar(255)   default ''                not null comment '分类图标',
    category_level    tinyint(1)          default 0                               comment '分类等级',
    parent_no    char(20) default ''                not null comment '父分类no',
    create_user   char(20)      default ''                not null comment '创建人',
    update_user   char(20)      default ''                not null comment '更新人',
    is_deleted    bit      default b'0'                not null comment '是否删除',
    create_time datetime     default CURRENT_TIMESTAMP null,
    update_time datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

INSERT INTO bookkeeping.sys_category (id, category_no, category_name, category_desc, category_icon, category_level, parent_no, create_user, update_user, is_deleted, create_time, update_time)
VALUES (1, '7365702540639481856',  '餐饮', '', 'icon', 1, '', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (2, '7365702540639481857',  '外卖', '', 'icon', 2, '7365702540639481856', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (3, '7365702540639481858',  '早餐', '', 'icon', 2, '7365702540639481856', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (4, '7365702540639481859', '午餐', '', 'icon', 2, '7365702540639481856', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (5, '7365702540639481860',  '晚餐', '', 'icon', 2, '7365702540639481856', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (6, '7365702540639481861', '零食', '', 'icon', 2, '7365702540639481856', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (7, '7365702540639481862', '水果', '', 'icon', 2, '7365702540639481856', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (8, '7365702540639481863', '饮料酒水', '', 'icon', 2, '7365702540639481856', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (9, '7365702540639481864', '餐厅美食', '', 'icon', 2, '7365702540639481856', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (10, '7365702540639481865',  '购物消费', '', 'icon', 1, '', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (11, '7365702540639481899',  '宠物用品', '', 'icon', 2, '7365702540639481865', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (12, '7365702540639481866',  '超市购物', '', 'icon', 2, '7365702540639481865', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (13, '7365702540639481867',  '电子数码', '', 'icon', 2, '7365702540639481865', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (14, '7365702540639481886',  '服饰鞋袜', '', 'icon', 2, '7365702540639481865', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (15, '7365702540639481868',  '护肤品', '', 'icon', 2, '7365702540639481865', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (16, '7365702540639481869',  '洗护用品', '', 'icon', 2, '7365702540639481865', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (17, '7365702540639481870',  '化妆品', '', 'icon', 2, '7365702540639481865', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (18, '7365702540639481871',  '日常用品', '', 'icon', 2, '7365702540639481865', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (19, '7365702540639481872',  '厨房用品', '', 'icon', 2, '7365702540639481865', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (20, '7365702540639481873',  '清洁用品', '', 'icon', 2, '7365702540639481865', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (21, '7365702540639481874',  '居家生活', '', 'icon', 1, '', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (22, '7365702540639481875',  '电费', '', 'icon', 2, '7365702540639481874', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (23, '7365702540639481876',  '水费', '', 'icon', 2, '7365702540639481874', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (24, '7365702540639481877',  '燃气费', '', 'icon', 2, '7365702540639481874', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (25, '7365702540639481878',  '网费', '', 'icon', 2, '7365702540639481874', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (26, '7365702540639481879',  '话费', '', 'icon', 2, '7365702540639481874', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (27, '7365702540639481880',  '物业费', '', 'icon', 2, '7365702540639481874', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (28, '7365702540639481881',  '取暖费', '', 'icon', 2, '7365702540639481874', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (29, '7365702540639481882',  '停车费', '', 'icon', 2, '7365702540639481874', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (30, '7365702540639481883',  '房租', '', 'icon', 2, '7365702540639481874', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (31, '7365702540639481884',  '柴米油盐', '', 'icon', 2, '7365702540639481874', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (32, '7365702540639481885',  '买菜做饭', '', 'icon', 2, '7365702540639481874', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (33, '7365702540639481886',  '行车交通', '', 'icon', 1, '', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (34, '7365702540639481887',  '油费', '', 'icon', 2, '7365702540639481886', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (35, '7365702540639481888',  '公交', '', 'icon', 2, '7365702540639481886', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (36, '7365702540639481889',  '地铁', '', 'icon', 2, '7365702540639481886', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (37, '7365702540639481890',  '火车', '', 'icon', 2, '7365702540639481886', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (38, '7365702540639481891',  '飞机', '', 'icon', 2, '7365702540639481886', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (39, '7365702540639481892',  '邮轮', '', 'icon', 2, '7365702540639481886', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (40, '7365702540639481893',  '打车', '', 'icon', 2, '7365702540639481886', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (41, '7365702540639481894',  '医疗费用', '', 'icon', 1, '', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (42, '7365702540639481895',  '门诊费', '', 'icon', 2, '7365702540639481894', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (43, '7365702540639481896',  '住院费', '', 'icon', 2, '7365702540639481894', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (44, '7365702540639481897',  '药品费', '', 'icon', 2, '7365702540639481894', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20'),
       (45, '7365702540639481898',  '护理费', '', 'icon', 2, '7365702540639481894', '', '', false, '2025-08-25 19:12:13', '2025-08-25 19:32:20')
;


create table user_category
(
    id          int auto_increment comment 'id'
        primary key,
    category_no     char(20)     default ''                not null comment '分类编号',
    user_no     char(20)     default ''                not null comment '用户编号',
    category_name    varchar(20)  default ''                not null comment '分类名称',
    category_desc     varchar(255)   default ''                not null comment '分类描述',
    category_icon varchar(255)   default ''                not null comment '分类图标',
    category_level    tinyint(1)          default 0                               comment '分类等级',
    parent_no    char(20) default ''                not null comment '父分类no',
    create_user   char(20)      default ''                not null comment '创建人',
    update_user   char(20)      default ''                not null comment '更新人',
    is_deleted    bit      default b'0'                not null comment '是否删除',
    create_time datetime     default CURRENT_TIMESTAMP null,
    update_time datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

CREATE TABLE third_party_user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_no CHAR(20) NOT NULL COMMENT '关联的系统用户ID',
  platform_type TINYINT NOT NULL COMMENT '平台类型：',
  open_id VARCHAR(128) COMMENT '平台用户唯一标识',
  union_id VARCHAR(128) COMMENT '平台用户统一标识（如微信unionId）',
  nickname VARCHAR(64) COMMENT '第三方平台昵称',
  avatar_url VARCHAR(512) COMMENT '头像URL',
  gender TINYINT COMMENT '性别：0-未知，1-男，2-女',
  country VARCHAR(64) COMMENT '国家',
  province VARCHAR(64) COMMENT '省份',
  city VARCHAR(64) COMMENT '城市',
  language VARCHAR(32) COMMENT '语言',
  extra_info TEXT COMMENT '额外信息（JSON格式）',
  create_user   char(20)      default ''                not null comment '创建人',
  update_user   char(20)      default ''                not null comment '更新人',
  is_deleted    bit      default b'0'                not null comment '是否删除',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX idx_platform_openid_user (platform_type, open_id, user_no)
) COMMENT='第三方用户信息表';

-- 文件存储表
CREATE TABLE IF NOT EXISTS `file_storage` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    `user_no` CHAR(20) NOT NULL default '' COMMENT '关联的系统用户ID',
    `file_hash` VARCHAR(100) NOT NULL COMMENT '文件hash',
    `file_name` VARCHAR(255) NOT NULL COMMENT '上传后的文件名称',
    `original_file_name` VARCHAR(255) NOT NULL COMMENT '文件原名称',
    `file_url` VARCHAR(500) NOT NULL COMMENT 'url',
    `file_type` VARCHAR(30) NOT NULL default '' COMMENT '文件类型',
    `file_size` int(11) NOT NULL default 0 COMMENT '文件大小',
    `bucket_name` VARCHAR(64) NOT NULL COMMENT 'bucket name',
    `storage_type` tinyint NOT NULL default  0 COMMENT '存储类型',
    `create_user`   char(20)      default ''                not null comment '创建人',
    `update_user`   char(20)      default ''                not null comment '更新人',
    `is_deleted`    bit      default b'0'                not null comment '是否删除',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件存储表';