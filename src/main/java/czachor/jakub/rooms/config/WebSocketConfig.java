package czachor.jakub.rooms.config;

import czachor.jakub.rooms.utils.ActiveUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@ComponentScan
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    final HttpHandshakeInterceptor interceptor;

    @Autowired
    public WebSocketConfig(HttpHandshakeInterceptor interceptor) {
        this.interceptor = interceptor;
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/room", "/user/");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .addInterceptors(interceptor)
                .withSockJS();
    }

    @Bean
    public ActiveUsers activeUsers(){
        return new ActiveUsers();
    }
}