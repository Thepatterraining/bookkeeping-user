package com.zt.bookkeeping.user.application.rpc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zt.bookkeeping.user.api.request.BatchQueryUserInfoRequest;
import com.zt.bookkeeping.user.api.response.BatchQueryUserInfoResponse;
import com.zt.bookkeeping.user.api.response.UserDTO;
import com.zt.bookkeeping.user.api.service.IUserService;
import com.zt.bookkeeping.user.domain.user.respository.UserRepository;
import com.zt.bookkeeping.user.infrastructure.db.UserMapper;
import com.zt.bookkeeping.user.infrastructure.db.entity.UserPO;
import com.zt.bookkeeping.user.infrastructure.util.LocalDateTimeUtil;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc:
 * ------------------------------------
 * Author:zt@meituan.com
 * Date:2025/9/11
 * Time:16:08
 */
@DubboService
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public BatchQueryUserInfoResponse batchQueryUserInfo(BatchQueryUserInfoRequest request) {
        // 获取用户编号
        List<String> userNoList = request.getUserNoList();
        LambdaQueryWrapper<UserPO>  wrapper = new LambdaQueryWrapper<>();
        wrapper.in(UserPO::getUserNo,userNoList);
        List<UserPO> userList = userMapper.selectList(wrapper);
        BatchQueryUserInfoResponse response = new BatchQueryUserInfoResponse();
        response.setUserDTOList(convert(userList));
        return response;
    }

    private List<UserDTO> convert(List<UserPO> userList) {
        return userList.stream().map(this::buildUserDTO).collect(Collectors.toList());
    }

    private UserDTO buildUserDTO(UserPO userPO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserNo(userPO.getUserNo());
        userDTO.setUserAvatar(userDTO.getUserAvatar());
        userDTO.setUserEmail(userDTO.getUserEmail());
        userDTO.setUserName(userDTO.getUserName());
        userDTO.setCreateTime(LocalDateTimeUtil.format(userPO.getCreateTime()));
        return userDTO;
    }


}
