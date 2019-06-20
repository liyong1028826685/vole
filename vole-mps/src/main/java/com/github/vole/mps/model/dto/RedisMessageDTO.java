package com.github.vole.mps.model.dto;

/**
 * @Author liyong
 * @Date 2019-06-20 09:57
 */
public class RedisMessageDTO extends MessageDTO{

    RedisMessageDTO(String id, String message) {
        super(id, message);
    }
    @Override
    String getMqType() {
        return "Redis";
    }
}
