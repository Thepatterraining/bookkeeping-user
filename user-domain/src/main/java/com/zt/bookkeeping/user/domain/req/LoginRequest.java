package com.zt.bookkeeping.user.domain.req;

import java.io.Serializable;

/**
 * Desc:
 * ------------------------------------
 * Author:zt@meituan.com
 * Date:2025/7/10
 * Time:20:13
 */
public class LoginRequest implements Serializable {
    private String username;
    private String password;
    private String mobile;
}
