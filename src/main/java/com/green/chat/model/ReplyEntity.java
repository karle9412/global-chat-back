package com.green.chat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamicInsert
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="REPLY_TB")
public class ReplyEntity {
  
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String boardNumber;

  @Column(nullable = false)
  private String replyContent;

  @Column(nullable = true, columnDefinition = "varchar(255) default sysdate")
  private String indate;

  @Column(nullable = true, columnDefinition = "varchar(255) default 0")
  private String step;

  @Column(nullable = true, columnDefinition = "varchar(255) default 1")
  private String lvl;

  @Column(nullable = true, columnDefinition = "varchar(255) default 1")
  private String bnum;
}
