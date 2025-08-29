package com.zt.bookkeeping.user.infrastructure.gateway.wechat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zt.bookkeeping.user.common.enums.ResultCode;
import com.zt.bookkeeping.user.domain.exception.UserLoginException;
import com.zt.bookkeeping.user.domain.gateway.wechat.WechatUserService;
import com.zt.bookkeeping.user.domain.gateway.wechat.model.WechatUserInfoBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信用户服务实现
 */
@Service
@Slf4j
public class WechatUserServiceImpl implements WechatUserService {

    @Value("${wechat.appid}")
    private String appId;

    @Value("${wechat.secret}")
    private String secret;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public WechatUserServiceImpl() {
        this.restTemplate = new RestTemplate();
        // 添加StringHttpMessageConverter以处理text/plain响应
        this.restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public WechatUserInfoBO getWechatInfo(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId +
                "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";

        try {
            // 使用String类型接收响应
            String responseStr = restTemplate.getForObject(url, String.class);
            log.info("调用微信API获取openId和unionId，响应：{}", responseStr);

            if (responseStr == null || responseStr.isEmpty()) {
                log.error("调用微信API失败，返回为空");
                throw new UserLoginException(ResultCode.INTERNAL_SERVER_ERROR);
            }

            // 手动将JSON字符串转换为Map
            Map<String, Object> response = objectMapper.readValue(responseStr, HashMap.class);

            WechatUserInfoBO wechatUserInfoBO = new WechatUserInfoBO();

            if (response.containsKey("errcode") && !response.get("errcode").equals(0)) {
                log.error("调用微信API失败，错误码：{}，错误信息：{}",
                        response.get("errcode"), response.get("errmsg"));
                wechatUserInfoBO.setErrcode((Integer) response.get("errcode"));
                wechatUserInfoBO.setErrmsg((String) response.get("errmsg"));
                return wechatUserInfoBO;
            }

            if (response.containsKey("openid")) {
                wechatUserInfoBO.setOpenid((String) response.get("openid"));
            }
            if (response.containsKey("unionid")) {
                wechatUserInfoBO.setUnionid((String) response.get("unionid"));
            }
            if (response.containsKey("session_key")) {
                wechatUserInfoBO.setSessionKey((String) response.get("session_key"));
            }

            return wechatUserInfoBO;
        } catch (Exception e) {
            log.error("调用微信API异常", e);
            throw new UserLoginException(ResultCode.INTERNAL_SERVER_ERROR);
        }
    }
}
