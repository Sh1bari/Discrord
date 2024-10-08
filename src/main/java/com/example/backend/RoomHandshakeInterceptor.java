package com.example.backend;

import lombok.*;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;
@Service
@RequiredArgsConstructor
public class RoomHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request, ServerHttpResponse response,
            WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        if (request instanceof ServletServerHttpRequest servletRequest) {
            String uri = servletRequest.getServletRequest().getRequestURI();
            String room = uri.substring(uri.lastIndexOf('/') + 1);
            attributes.put("room", room);
        }
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }
}
