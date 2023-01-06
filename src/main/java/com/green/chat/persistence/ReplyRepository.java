package com.green.chat.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.chat.model.ReplyEntity;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, String> {

  List<ReplyEntity> findByBoardNumber(String boardNumber);

}
