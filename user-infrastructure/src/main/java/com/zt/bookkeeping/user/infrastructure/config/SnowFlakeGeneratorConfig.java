package com.zt.bookkeeping.user.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "snowflake")
public class SnowFlakeGeneratorConfig {

    /**
     * 机器id
     */
    private int machineId;
}
