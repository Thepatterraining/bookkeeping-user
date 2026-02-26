package com.zt.bookkeeping.user.api.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LoiydZhou
 * @date 2025/9/13
 */
@Data
public class UserDTO  implements Serializable {
    private String userNo;
    private String userName;
    private String userAvatar;
    private String userEmail;
    private String createTime;
}
