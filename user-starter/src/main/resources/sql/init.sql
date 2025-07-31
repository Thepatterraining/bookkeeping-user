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