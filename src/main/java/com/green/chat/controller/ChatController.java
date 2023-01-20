package com.green.chat.controller;

import ch.qos.logback.core.net.SyslogOutputStream;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import com.green.chat.dto.Message;

@Controller
@EnableWebSocketMessageBroker
public class ChatController {
    //111
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/message") // /app/message
    @SendTo("/chatroom/public")
    public Message receivePublicMessage(@Payload Message message){

        System.out.println("전체:"+message);
        return message;
    }
    
    @MessageMapping("/private-message")
    private Message receivePrivateMessage(@Payload Message message){
        

        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message); // /user/david/private
        System.out.println("개인:"+message);
        return message;
    }
}