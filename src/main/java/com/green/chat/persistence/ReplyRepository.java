package com.green.chat.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.green.chat.model.ReplyEntity;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, String> {

  List<ReplyEntity> findByBoardNumber(String boardNumber);

  @Query(value="SELECT MAX(BNUM) FROM REPLY_TB WHERE BOARD_NUMBER = ?", nativeQuery = true)
  String getMaxBnum(String boardNumber);

  @Query(value = "SELECT MAX(LVL) FROM REPLY_TB WHERE BOARD_NUMBER =? AND STEP = ?", nativeQuery = true)
  String getMaxLvl(String boardNumber, String step);

}
