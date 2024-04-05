package com.chatapp.serviceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.entity.Messages;
import com.chatapp.entity.User;
import com.chatapp.repository.MessagesRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.MessagesService;

@Service
public class MessagesServiceImpl implements MessagesService {

	@Autowired
	MessagesRepository messagesRepository;
	

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public void processAndSendMessage(String messageContent, Integer groupId, int userId, LocalDateTime timestamp)
	{
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent() && validateMessage(messageContent)) {
            Messages messages = new Messages();
            messages.setMessageContent(messageContent);
            messages.setGroupId(groupId);
            messages.setUserId(userId);
            messages.setTimestamp(timestamp);
            messagesRepository.save(messages);
            
        } else {
            userRepository.deleteById(userId);
        }
	}
	
    	@Override
    	public boolean validateMessage(String messageContent) {
    		
    		return true;
    	}

		
	
}
