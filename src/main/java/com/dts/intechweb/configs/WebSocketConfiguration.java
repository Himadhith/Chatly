package com.dts.intechweb.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
    @Value("${chat.app.ws-endpoint-path}")
    private String wsEndpointPath;
    @Value("${chat.app.application-destination-prefix}")
    private String applicationDestinationPrefix;
    @Value("${chat.app.broker-destination-prefix}")
    private String brokerDestinationPrefix;


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(wsEndpointPath).withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes(applicationDestinationPrefix);
        registry.enableSimpleBroker(brokerDestinationPrefix);
    }

}
