package com.green.chat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.green.chat.model.ReplyEntity;
import com.green.chat.persistence.ReplyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReplyService {
  
  @Autowired
  private ReplyRepository replyRepository;

  public void create(ReplyEntity replyEntity) {
    replyRepository.save(replyEntity);
  }

  public List<ReplyEntity> getreply(String boardNumber) {
    return replyRepository.findByBoardNumber(boardNumber);
  }

  public ReplyEntity getById(String id) {
    return replyRepository.getById(id);
  }

  public void delete(ReplyEntity reply) {
    replyRepository.delete(reply);
  }

  public String getMaxBnum(String boardNumber) {
    return replyRepository.getMaxBnum(boardNumber);
  }

  public String getMaxLvl(String boardNumber, String Step) {
    return replyRepository.getMaxLvl(boardNumber, Step);
  }
}
