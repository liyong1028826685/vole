package com.github.vole.mps.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.vole.common.constants.CommonConstant;
import com.github.vole.mps.constants.MessageTopicConstant;
import com.github.vole.mps.mapper.SysZuulRouteMapper;
import com.github.vole.mps.model.dto.MessageDTO;
import com.github.vole.mps.model.dto.RedisMessageDTO;
import com.github.vole.mps.model.entity.SysZuulRoute;
import com.github.vole.mps.service.PublisherService;
import com.github.vole.mps.service.SysZuulRouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * @author liyong
 */
@Service
public class SysZuulRouteServiceImpl extends ServiceImpl<SysZuulRouteMapper, SysZuulRoute> implements SysZuulRouteService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PublisherService publisherService;

    /**
     * 立即生效配置
     *
     * @return
     */
    @Override
    public Boolean applyZuulRoute() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(CommonConstant.DEL_FLAG, CommonConstant.STATUS_NORMAL);
        List<SysZuulRoute> routeList = this.list(wrapper);

        redisTemplate.opsForValue().set(CommonConstant.ROUTE_KEY, routeList);

        MessageDTO messageDTO = RedisMessageDTO.builder().id(UUID.randomUUID().toString()).message("动态路由发生变更").build();
        publisherService.sendMessage(MessageTopicConstant.ROUTE_CHANGE_MESSAGE, messageDTO);

        logger.info("推送MQ消息:{}", messageDTO);
        return Boolean.TRUE;
    }
}
