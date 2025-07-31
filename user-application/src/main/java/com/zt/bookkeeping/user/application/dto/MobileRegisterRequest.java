package com.zt.bookkeeping.user.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc:
 * ------------------------------------
 * Author:zt@meituan.com
 * Date:2025/7/10
 * Time:20:13
 */
@Getter
@Setter
public class MobileRegisterRequest implements Serializable {

    @NotBlank
    private String code;

    @NotBlank
    private String mobile;
}
