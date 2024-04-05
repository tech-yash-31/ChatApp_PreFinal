package com.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.config.ChatMessageHandler;
import com.chatapp.dto.MessagesDTO;
import com.chatapp.serviceImpl.MessagesServiceImpl;

@RestController
public class MessagesController {

	@Autowired
	private MessagesServiceImpl messagesServiceImpl;

	@Autowired
	private ChatMessageHandler handleMessage;

	@PostMapping("/topic/send/{communityName}")
	public ResponseEntity<?> sendMessage(@PathVariable String communityName, @RequestBody MessagesDTO messagesDTO) {
		messagesServiceImpl.processAndSendMessage(messagesDTO.getMessageContent(), messagesDTO.getGroupId(),
				messagesDTO.getUserId(), messagesDTO.getTimestamp());
		
		handleMessage.broadcastToCommunity(communityName, messagesDTO.getMessageContent());
		return ResponseEntity.ok().build();
	}
}
