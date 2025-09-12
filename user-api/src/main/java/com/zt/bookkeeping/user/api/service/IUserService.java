package com.zt.bookkeeping.user.api.service;

import com.zt.bookkeeping.user.api.request.BatchQueryUserInfoRequest;
import com.zt.bookkeeping.user.api.response.BatchQueryUserInfoResponse;

/**
 * Desc:
 * ------------------------------------
 * Author:zt@meituan.com
 * Date:2025/9/11
 * Time:16:06
 */
public interface IUserService {
    BatchQueryUserInfoResponse batchQueryUserInfo(BatchQueryUserInfoRequest request);
}
