package com.green.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {

  private String id;
  private String email;
  private String boardNumber;
  private String replyContent;
  private String indate;
  private String step;
  private String lvl;
  private String bnum;
  
}
