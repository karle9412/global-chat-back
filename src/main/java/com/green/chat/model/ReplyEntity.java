package com.green.Entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "REPLY_TB")
@DynamicInsert
@Getter
@Setter
public class ReplyEntity {
  
  @Id
  private Number replyId;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private Number filNumber;

  @Column(length = 300, nullable = false)
  private String replyContent;

  private LocalDate indate = LocalDate.now();

  @Column(nullable = false)
  private Number step = 0;

  @Column(nullable = false)
  private Number lvl = 0;

  @Column(nullable = false)
  private Number bnum = 0;
}
