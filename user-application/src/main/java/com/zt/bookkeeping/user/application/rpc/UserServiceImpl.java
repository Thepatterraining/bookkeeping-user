package com.zt.bookkeeping.user.application.rpc;

import com.zt.bookkeeping.user.api.request.BatchQueryUserInfoRequest;
import com.zt.bookkeeping.user.api.response.BatchQueryUserInfoResponse;
import com.zt.bookkeeping.user.api.service.IUserService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * Desc:
 * ------------------------------------
 * Author:zt@meituan.com
 * Date:2025/9/11
 * Time:16:08
 */
@DubboService
public class UserServiceImpl implements IUserService {

    @Override
    public BatchQueryUserInfoResponse batchQueryUserInfo(BatchQueryUserInfoRequest request) {
        return null;
    }
}
