package com.poker.server.framework.websocket.config;

import com.poker.server.framework.mq.redis.config.PokerServerRedisMQConsumerAutoConfiguration;
import com.poker.server.framework.mq.redis.core.RedisMQTemplate;
import com.poker.server.framework.websocket.core.handler.JsonWebSocketMessageHandler;
import com.poker.server.framework.websocket.core.listener.WebSocketMessageListener;
import com.poker.server.framework.websocket.core.security.LoginUserHandshakeInterceptor;
import com.poker.server.framework.websocket.core.security.WebSocketAuthorizeRequestsCustomizer;
import com.poker.server.framework.websocket.core.sender.local.LocalWebSocketMessageSender;
import com.poker.server.framework.websocket.core.sender.redis.RedisWebSocketMessageConsumer;
import com.poker.server.framework.websocket.core.sender.redis.RedisWebSocketMessageSender;
import com.poker.server.framework.websocket.core.session.WebSocketSessionHandlerDecorator;
import com.poker.server.framework.websocket.core.session.WebSocketSessionManager;
import com.poker.server.framework.websocket.core.session.WebSocketSessionManagerImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;

@AutoConfiguration(before = PokerServerRedisMQConsumerAutoConfiguration.class)
@EnableWebSocket
@ConditionalOnProperty(prefix = "pokerServer.websocket", value = "enable", matchIfMissing = true)
@EnableConfigurationProperties(WebSocketProperties.class)
public class PokerServerWebSocketAutoConfiguration {

    @Bean
    public WebSocketConfigurer webSocketConfigurer(HandshakeInterceptor[] handshakeInterceptors,
                                                   WebSocketHandler webSocketHandler,
                                                   WebSocketProperties webSocketProperties) {
        return registry -> registry
                .addHandler(webSocketHandler, webSocketProperties.getPath())
                .addInterceptors(handshakeInterceptors)
                .setAllowedOriginPatterns("*");
    }

    @Bean
    public HandshakeInterceptor handshakeInterceptor() {
        return new LoginUserHandshakeInterceptor();
    }

    @Bean
    public WebSocketHandler webSocketHandler(WebSocketSessionManager sessionManager,
                                             List<? extends WebSocketMessageListener<?>> messageListeners) {
        JsonWebSocketMessageHandler messageHandler = new JsonWebSocketMessageHandler(messageListeners);
        return new WebSocketSessionHandlerDecorator(messageHandler, sessionManager);
    }

    @Bean
    public WebSocketSessionManager webSocketSessionManager() {
        return new WebSocketSessionManagerImpl();
    }

    @Bean
    public WebSocketAuthorizeRequestsCustomizer webSocketAuthorizeRequestsCustomizer(WebSocketProperties webSocketProperties) {
        return new WebSocketAuthorizeRequestsCustomizer(webSocketProperties);
    }

    @Configuration
    @ConditionalOnProperty(prefix = "pokerServer.websocket", name = "sender-type", havingValue = "local", matchIfMissing = true)
    public class LocalWebSocketMessageSenderConfiguration {

        @Bean
        public LocalWebSocketMessageSender localWebSocketMessageSender(WebSocketSessionManager sessionManager) {
            return new LocalWebSocketMessageSender(sessionManager);
        }

    }

    @Configuration
    @ConditionalOnProperty(prefix = "pokerServer.websocket", name = "sender-type", havingValue = "redis")
    public class RedisWebSocketMessageSenderConfiguration {

        @Bean
        public RedisWebSocketMessageSender redisWebSocketMessageSender(WebSocketSessionManager sessionManager,
                                                                       RedisMQTemplate redisMQTemplate) {
            return new RedisWebSocketMessageSender(sessionManager, redisMQTemplate);
        }

        @Bean
        public RedisWebSocketMessageConsumer redisWebSocketMessageConsumer(
                RedisWebSocketMessageSender redisWebSocketMessageSender) {
            return new RedisWebSocketMessageConsumer(redisWebSocketMessageSender);
        }

    }

}
