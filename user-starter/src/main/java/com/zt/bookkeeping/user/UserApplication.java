package com.zt.bookkeeping.user;

import com.zt.bookkeeping.user.infrastructure.config.SnowFlakeGeneratorConfig;
import com.zt.bookkeeping.user.infrastructure.config.TaskThreadPoolThirdCooperateConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan({"com.zt.bookkeeping.user.**.db"})
@EnableConfigurationProperties({TaskThreadPoolThirdCooperateConfig.class, SnowFlakeGeneratorConfig.class})
@EnableAsync
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
