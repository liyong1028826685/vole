package com.github.vole.mps.service;

import com.github.vole.mps.model.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 使用redis发送消息 如果有其他MQ可以使用 来同步消息
 *
 * @Author liyong
 * @Date 2019-06-20 09:53
 */
@Service
public class PublisherService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 发布消息接口
     *
     * @param channel
     * @param message
     */
    public boolean sendMessage(String channel, MessageDTO message) {
        try {
            redisTemplate.convertAndSend(channel, message);
            return true;
        } catch (Exception e) {
            logger.info("消息发送失败！异常信息:", e);
            return false;
        }
    }
}
