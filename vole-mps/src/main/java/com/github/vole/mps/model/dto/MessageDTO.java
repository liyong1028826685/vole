package com.github.vole.mps.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * MQ消息
 *
 * @Author liyong
 * @Date 2019-06-20 09:55
 */
@Builder
@Data
public abstract class MessageDTO {
    /**
     * 消息id
     */
    private String id;
    /**
     * json对象
     */
    private String message;

    /**
     * mq类型
     * @return
     */
    abstract String getMqType();
}
