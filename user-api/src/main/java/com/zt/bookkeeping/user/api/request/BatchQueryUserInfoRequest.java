package com.zt.bookkeeping.user.api.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 * ------------------------------------
 * Author:zt@meituan.com
 * Date:2025/9/11
 * Time:16:17
 */
@Setter
@Getter
public class BatchQueryUserInfoRequest implements Serializable {
    private List<String> userNoList;
}
