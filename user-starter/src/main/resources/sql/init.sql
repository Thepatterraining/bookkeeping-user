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
    ledger_status tinyint      default 0                 not null comment '账本状态',
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
    create_time datetime     default CURRENT_TIMESTAMP null,
    update_time datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);