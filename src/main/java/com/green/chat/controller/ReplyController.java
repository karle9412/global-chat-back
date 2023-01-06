package com.green.chat.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.chat.dto.ReplyDTO;
import com.green.chat.dto.ReplyUpdateDTO;
import com.green.chat.model.ReplyEntity;
import com.green.chat.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/reply")
public class ReplyController {

  @Autowired
  private ReplyService replyService;

  @PostMapping("/register")
  public void register(@AuthenticationPrincipal String user_email, @RequestBody ReplyDTO replyDTO) {

    ReplyEntity register = ReplyEntity.builder()
        .email(user_email)
        .boardNumber(replyDTO.getBoardNumber())
        .replyContent(replyDTO.getReplyContent())
        .build();

    replyService.create(register);
  }

  @GetMapping("/request")
    public ResponseEntity<?> getreply(@RequestParam String boardNumber) {

        List<ReplyEntity> entities = replyService.getreply(boardNumber);

        return ResponseEntity.ok(entities);
    }

    @PutMapping("/replyupdate/{id}")
    public void replyUpdate(@PathVariable String id, @RequestBody ReplyUpdateDTO replyUpdateDTO) {

      ReplyEntity reply = replyService.getById(id);
      System.out.println("댓글" + reply);

      reply.setReplyContent(replyUpdateDTO.getReplyContent());

      replyService.create(reply);

  }

  @DeleteMapping("/delete/{id}")
  public void replyDelete(@PathVariable String id){
    ReplyEntity reply = replyService.getById(id);
    replyService.delete(reply);
  
  }

}
