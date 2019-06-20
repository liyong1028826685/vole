package com.github.vole.gateway.config;

import com.github.vole.gateway.constants.MessageTopicConstant;
import com.github.vole.gateway.listener.DynamicRouteListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @Author liyong
 * @Date 2019-06-20 09:42
 */
@Configuration
public class RedisReceiverConfig {

    private static Logger logger = LoggerFactory.getLogger(RedisReceiverConfig.class);

    /**
     * redis消息监听器容器
     * 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
     * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     *
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅了一个叫具体的 的通道
        container.addMessageListener(listenerAdapter, new PatternTopic(MessageTopicConstant.ROUTE_CHANGE_MESSAGE));
        //这个container 可以添加多个 messageListener
        logger.info("redis 对监听主题是：{}", MessageTopicConstant.ROUTE_CHANGE_MESSAGE);
        return container;
    }

    /**
     * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
     *
     * @param receiver
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(DynamicRouteListener receiver) {
        /*这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
        也有好几个重载方法，这边默认调用处理器的方法 叫handleMessage 可以自己到源码里面看

        这里实质就是监听调用ReceiverService类中的receiveMessage方法
        */
        logger.info("redis 的监听处理类方法为：{}", "com.autohome.outerapi.service.ReceiverService.receiveMessage()");
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
