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
import oracle.jdbc.proxy.annotation.Post;

@Slf4j
@RestController
@RequestMapping("/reply")
public class ReplyController {

  @Autowired
  private ReplyService replyService;

  @PostMapping("/register/{boardNumber}")
  public void register(@PathVariable String boardNumber, @AuthenticationPrincipal String user_email, @RequestBody ReplyDTO replyDTO) {

    String maxbnum = replyService.getMaxBnum(boardNumber);

    ReplyEntity register = null;

    if(replyService.getreply(boardNumber) == null){

      register = ReplyEntity.builder()
        .email(user_email)
        .boardNumber(replyDTO.getBoardNumber())
        .replyContent(replyDTO.getReplyContent())
        .build();
    } else {
      int bnum = Integer.parseInt(maxbnum) + 1;
      register = ReplyEntity.builder()
        .email(user_email)
        .boardNumber(replyDTO.getBoardNumber())
        .replyContent(replyDTO.getReplyContent())
        .bnum(Integer.toString(bnum))
        .build();
    }

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

  @PostMapping("/replyrequire")
  public void replyRequire(@AuthenticationPrincipal String user_email, @RequestBody ReplyDTO replyDTO) {
    
    ReplyEntity reply = new ReplyEntity();

    int step = Integer.parseInt(replyDTO.getStep());
    String maxLvl = replyService.getMaxLvl(replyDTO.getBoardNumber(), replyDTO.getStep()+1);
    int bnum = Integer.parseInt(replyDTO.getBnum());

    String requireStep = Integer.toString(step + 1);

    ReplyEntity register = null;

    if(maxLvl == null) {
      register = ReplyEntity.builder()
      .email(user_email)
      .boardNumber(replyDTO.getBoardNumber())
      .replyContent(replyDTO.getReplyContent())
      .bnum(Integer.toString(bnum))
      .step(requireStep)
      .build();
    } else {

      String requireLvl = Integer.toString(Integer.parseInt(maxLvl) + 1);
      register = ReplyEntity.builder()
      .email(user_email)
      .boardNumber(replyDTO.getBoardNumber())
      .replyContent(replyDTO.getReplyContent())
      .bnum(Integer.toString(bnum))
      .step(requireStep)
      .lvl(requireLvl)
      .build();
    }
    
    replyService.create(register);
  }
  

}
