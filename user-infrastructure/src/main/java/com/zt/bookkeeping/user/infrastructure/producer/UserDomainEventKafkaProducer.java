package com.zt.bookkeeping.user.infrastructure.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zt.bookkeeping.user.common.base.AbstractEvent;
import com.zt.bookkeeping.user.domain.user.entity.UserAgg;
import com.zt.bookkeeping.user.domain.user.event.UserLoggedInEvent;
import com.zt.bookkeeping.user.domain.user.event.UserRegisteredEvent;
import com.zt.bookkeeping.user.infrastructure.config.KafkaTopicConfig;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class UserDomainEventKafkaProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private KafkaTopicConfig kafkaTopicConfig;

    @EventListener
    public void publishUserLoggedInEvent(UserLoggedInEvent event) {
        sendEvent(kafkaTopicConfig.getUserLogin(), event);
    }

    @EventListener
    public void publishUserRegisteredEvent(UserRegisteredEvent event) {
        sendEvent(kafkaTopicConfig.getUserRegister(), event);
    }

    private void sendEvent(String topic, AbstractEvent<?> event) {
        final String payload;
        try {
            payload = buildPayload(event);
        } catch (JsonProcessingException ex) {
            log.error("Kafka消息序列化失败, topic={}, eventId={}, eventType={}", topic, event.getEventId(), event.getEventType(), ex);
            return;
        }

        CompletableFuture<SendResult<String, String>> sendFuture = kafkaTemplate.send(topic, event.getEventId(), payload);
        sendFuture.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Kafka消息发送失败, topic={}, eventId={}, eventType={}", topic, event.getEventId(), event.getEventType(), ex);
                return;
            }
            if (result == null || result.getRecordMetadata() == null) {
                log.error("Kafka消息发送结果异常, topic={}, eventId={}, eventType={}", topic, event.getEventId(), event.getEventType());
                return;
            }
            log.info("Kafka消息发送成功, topic={}, partition={}, offset={}, eventId={}, eventType={}",
                    topic,
                    result.getRecordMetadata().partition(),
                    result.getRecordMetadata().offset(),
                    event.getEventId(),
                    event.getEventType());
        });
    }

    private String buildPayload(AbstractEvent<?> event) throws JsonProcessingException {
        Map<String, Object> payload = new HashMap<>();
        payload.put("eventId", event.getEventId());
        payload.put("eventType", event.getEventType());
        payload.put("eventTime", event.getEventTime());
        payload.put("eventData", buildEventData(event.getEventData()));
        return objectMapper.writeValueAsString(payload);
    }

    private Object buildEventData(Object eventData) {
        if (!(eventData instanceof UserAgg userAgg)) {
            return String.valueOf(eventData);
        }
        Map<String, Object> userPayload = new HashMap<>();
        userPayload.put("userNo", userAgg.getUserNo());
        userPayload.put("username", userAgg.getUsername());
        userPayload.put("mobile", userAgg.getMobile());
        return userPayload;
    }
}

