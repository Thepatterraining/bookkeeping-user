package com.zt.bookkeeping.user.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zt.bookkeeping.user.domain.user.entity.UserAgg;
import com.zt.bookkeeping.user.domain.user.entity.UserStatus;
import com.zt.bookkeeping.user.domain.user.entity.UserType;
import com.zt.bookkeeping.user.domain.user.respository.UserRepository;
import com.zt.bookkeeping.user.infrastructure.db.UserMapper;
import com.zt.bookkeeping.user.infrastructure.db.entity.UserPO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserAgg getUser(String username) {
        // 1. 查询用户信息
        LambdaQueryWrapper<UserPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserPO::getUsername, username);
        UserPO userPO = userMapper.selectOne(queryWrapper);
        if (userPO == null) {
            return null;
        }

        // 2. 转化成聚合返回
        return this.toEntity(userPO);
    }

    @Override
    public UserAgg getUserByMobile(String mobile) {
        // 1. 查询用户信息
        LambdaQueryWrapper<UserPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserPO::getMobile, mobile);
        UserPO userPO = userMapper.selectOne(queryWrapper);
        if (userPO == null) {
            return null;
        }

        // 2. 转化成聚合返回
        return this.toEntity(userPO);
    }

    @Override
    public void insert(UserAgg userAgg) {
        UserPO userPO = this.toPO(userAgg);
        userMapper.insert(userPO);
        // 回填id
        userAgg.setId(userPO.getId());
    }

    private UserPO toPO(UserAgg userAgg) {
        return UserPO.builder()
                .userNo(userAgg.getUserNo())
                .username(userAgg.getUsername())
                .password(userAgg.getPassword())
                .email(userAgg.getEmail())
                .mobile(userAgg.getMobile())
                .gender(userAgg.getGender())
                .age(userAgg.getAge())
                .userStatus(userAgg.getUserStatus().getCode())
                .userType(userAgg.getUserType().getCode())
                .build();
    }

    private UserAgg toEntity(UserPO doObj) {
        return UserAgg.builder()
                .id(doObj.getId())
                .userNo(doObj.getUserNo())
                .username(doObj.getUsername())
                .password(doObj.getPassword())
                .email(doObj.getEmail())
                .mobile(doObj.getMobile())
                .gender(doObj.getGender())
                .age(doObj.getAge())
                .userStatus(UserStatus.of(doObj.getUserStatus()))
                .userType(UserType.of(doObj.getUserType()))
                .createTime(doObj.getCreateTime())
                .updateTime(doObj.getUpdateTime())
                .build();
    }
}
