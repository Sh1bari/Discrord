package com.example.backend.configs;

import com.example.backend.RoomHandshakeInterceptor;
import com.example.backend.VoiceWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final VoiceWebSocketHandler voiceWebSocketHandler;
    private final RoomHandshakeInterceptor roomHandshakeInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(voiceWebSocketHandler, "/voice/{room}")
                .setAllowedOrigins("*")
                .addInterceptors(roomHandshakeInterceptor);
    }
}