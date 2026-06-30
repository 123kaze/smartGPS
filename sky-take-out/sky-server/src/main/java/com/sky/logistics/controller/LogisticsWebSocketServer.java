package com.sky.logistics.controller;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
@ServerEndpoint("/api/v1/ws")
public class LogisticsWebSocketServer {

    @OnOpen
    public void onOpen(Session session) throws IOException {
        session.getBasicRemote().sendText("{\"channel\":\"system.connected\",\"data\":{\"status\":\"STARTER\"}}");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        session.getBasicRemote().sendText("{\"channel\":\"system.echo\",\"data\":" + quote(message) + "}");
    }

    @OnClose
    public void onClose(Session session) {
    }

    private String quote(String value) {
        if (value == null) {
            return "\"\"";
        }
        return "\"" + value.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
    }
}
