package com.green.chat.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.chat.model.MessageEntity;


public interface MessageRepository extends JpaRepository<MessageEntity,Long> {


    List<MessageEntity> findBySenderNameAndReceiverName(String username1, String username2);

    // List<MessageEntity> findBySenderNameAndReceiverNameOrReceiverNameAndSenderName(String username1, String username2, String username3, String username4);
    
}
