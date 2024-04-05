package com.chatapp.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MessagesDTO {
	
	private String messageContent;
    private Integer groupId;
    private int userId;
    private LocalDateTime timestamp;

}