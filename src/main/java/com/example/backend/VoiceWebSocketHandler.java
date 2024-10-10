package com.example.backend;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
@RequiredArgsConstructor
public class VoiceWebSocketHandler extends TextWebSocketHandler {

    // Карта комнат и их сессий
    private final Map<String, Set<WebSocketSession>> roomSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String room = getRoom(session);
        roomSessions.computeIfAbsent(room, k -> ConcurrentHashMap.newKeySet()).add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String room = getRoom(session);
        Set<WebSocketSession> sessions = roomSessions.get(room);

        if (sessions != null) {
            // Рассылка SDP или ICE сообщений всем клиентам комнаты
            for (WebSocketSession wsSession : sessions) {
                if (wsSession.isOpen() && !session.getId().equals(wsSession.getId())) {
                    wsSession.sendMessage(message);
                }
            }
        }
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        String room = getRoom(session);
        Set<WebSocketSession> sessions = roomSessions.get(room);

        if (sessions != null) {
            // Пересылка аудио данных всем клиентам комнаты
            for (WebSocketSession wsSession : sessions) {
                if (wsSession.isOpen() && !session.getId().equals(wsSession.getId())) {
                    try {
                        wsSession.sendMessage(message);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String room = getRoom(session);
        Set<WebSocketSession> sessions = roomSessions.get(room);

        if (sessions != null) {
            sessions.remove(session);
            if (sessions.isEmpty()) {
                roomSessions.remove(room);
            }
        }
    }

    private String getRoom(WebSocketSession session) {
        return (String) session.getAttributes().get("room");
    }
}
