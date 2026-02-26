package com.zt.bookkeeping.user.api.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 * ------------------------------------
 * Author:zt@meituan.com
 * Date:2025/9/11
 * Time:16:18
 */
@Getter
@Setter
public class BatchQueryUserInfoResponse implements Serializable {
    List<UserDTO> userDTOList;
}
