package com.chatapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class ChatMessageHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(ChatMessageHandler.class);
    // Stores sessions indexed by community names 
    // String stores the Community name as key and WebSocketSession stores the sessions information
    private final Map<String, Set<WebSocketSession>> communitySessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String communityName = getCommunityName(session);
        communitySessions.computeIfAbsent(communityName, k -> new HashSet<>()).add(session);
        logger.info("Session added to community: " + communityName);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String communityName = getCommunityName(session);
        // Broadcast the message within community
        broadcastToCommunity(communityName, message.getPayload());
        
    }
    
    public  void broadcastToCommunity(String communityName, String message) {
        Set<WebSocketSession> sessions = communitySessions.getOrDefault(communityName, new HashSet<>());
        for (WebSocketSession session : sessions) {
            try {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(message));
                }
            } catch (IOException e) {
                logger.error("Error sending message in community: " + communityName, e);
            }
        }
    }

    //extract community name from the session URI
    private String getCommunityName(WebSocketSession session) {
        // Extract the community name from the URI
        String path = session.getUri().getPath();
        return path.substring(path.lastIndexOf('/') + 1);
    }
}