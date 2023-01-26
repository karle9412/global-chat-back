package com.green.chat.dto;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
//1111
    private String senderName;
    private String receiverName;
    private String message;
    private LocalDateTime date = LocalDateTime.now();
    private Status status;
}