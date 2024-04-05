package com.chatapp.service;

import java.time.LocalDateTime;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import com.chatapp.entity.Messages;

public interface MessagesService {

	public void processAndSendMessage(String messageContent, Integer groupId, int userId, LocalDateTime timestamp);
	public boolean validateMessage(String messageContent);
}
