package com.github.vole.mps.model.dto;

import lombok.Builder;

/**
 * @Author liyong
 * @Date 2019-06-20 09:57
 */
public class RedisMessageDTO extends MessageDTO {
    @Builder
    public RedisMessageDTO(String id, String message) {
        super(id, message);
    }
}
