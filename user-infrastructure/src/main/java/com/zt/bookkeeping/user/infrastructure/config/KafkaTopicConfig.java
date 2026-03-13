package com.zt.bookkeeping.user.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "bookkeeping.kafka.topic")
public class KafkaTopicConfig {

    private String userLogin = "bookkeeping.user.login";

    private String userRegister = "bookkeeping.user.register";
}

