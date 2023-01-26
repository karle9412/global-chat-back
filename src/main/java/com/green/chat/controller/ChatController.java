package com.green.chat.controller;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import com.green.chat.dto.Greeting;
import com.green.chat.dto.HelloMessage;
import com.green.chat.dto.Message;

@Controller
public class ChatController {
    //111
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    private Map<String, String> userSessionMap = new HashMap<>();
    private List<String> waitingList = new ArrayList<>();


    @MessageMapping("/message") // /app/message
    @SendTo("/chatroom/public")
    public Message receivePublicMessage(@Payload Message message, SimpMessageHeaderAccessor headerAccessor){

        String sessionId = headerAccessor.getSessionId();
        
        System.out.println("세션"+sessionId);
        System.out.println("전체:"+message);
        return message;
    }
    
    @MessageMapping("/private-message")
    private Message receivePrivateMessage(@Payload Message message){
        

        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message); // /user/david/private
        System.out.println("개인:"+message);
        return message;
    }

    @MessageMapping("/hello")
   
    public Greeting greeting(HelloMessage message) throws Exception {
        System.out.println("으아아아아아ㅏ아앙"+message);

        simpMessagingTemplate.convertAndSendToUser("test3","/alarm",message.getCont());
        Thread.sleep(1000); // simulated delay
        // return new Greeting("Hello, " + message.getName() + "!");
        return new Greeting("Hello, !");
    }

    @MessageMapping("/random")
    private Message RandomMessage(@Payload Message message){
        
        simpMessagingTemplate.convertAndSend("/random",message);
        
        return message;

    }
}