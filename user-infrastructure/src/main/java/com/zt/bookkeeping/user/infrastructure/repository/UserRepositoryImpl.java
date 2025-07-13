package com.zt.bookkeeping.user.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zt.bookkeeping.user.domain.user.entity.UserAgg;
import com.zt.bookkeeping.user.domain.user.entity.UserStatus;
import com.zt.bookkeeping.user.domain.user.entity.UserType;
import com.zt.bookkeeping.user.domain.user.respository.UserRepository;
import com.zt.bookkeeping.user.infrastructure.db.UserMapper;
import com.zt.bookkeeping.user.infrastructure.db.entity.UserDO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserAgg getUser(String username) {
        // 1. 查询用户信息
        LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserDO::getUsername, username);
        UserDO userDo = userMapper.selectOne(queryWrapper);
        if (userDo == null) {
            return null;
        }

        // 2. 转化成聚合返回
        return this.toEntity(userDo);
    }

    private UserAgg toEntity(UserDO doObj) {
        return new UserAgg(
                doObj.getId(),
                doObj.getUsername(),
                doObj.getPassword(),
                doObj.getEmail(),
                doObj.getMobile(),
                doObj.getGender(),
                doObj.getAge(),
                UserStatus.of(doObj.getUserStatus()),
                UserType.of(doObj.getUserType())
        );
    }
}
