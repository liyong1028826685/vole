package com.github.vole.mps.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MQ消息
 *
 * @Author liyong
 * @Date 2019-06-20 09:55
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDTO {
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
    String getMqType(){
        return "Redis";
    }
}
