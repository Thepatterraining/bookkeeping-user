package com.zt.bookkeeping.user.api.response;

import lombok.Data;

/**
 * @author LoiydZhou
 * @date 2025/9/13
 */
@Data
public class UserDTO {
    private String userNo;
    private String userName;
    private String userAvatar;
    private String userEmail;
    private String createTime;
}
