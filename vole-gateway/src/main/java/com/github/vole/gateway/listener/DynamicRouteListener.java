package com.github.vole.gateway.listener;

import com.github.vole.gateway.config.RedisReivceConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 路由规则变化监听器
 *
 * @Author liyong
 * @Date 2019-06-20 09:39
 */
@Component("dynamicRouteListener")
public class DynamicRouteListener {

    private static Logger logger = LoggerFactory.getLogger(DynamicRouteListener.class);

    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    RouteLocator routeLocator;

    /**
     * 消息处理
     *
     * @param message
     */
    public void receiveMessage(String message) {
        try {
            logger.info("收到的mq消息:{}", message);
            //消息处理
            refreshRoute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 刷新路由规则到zuul
     */
    public void refreshRoute() {
        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);
        publisher.publishEvent(routesRefreshedEvent);
    }
}
