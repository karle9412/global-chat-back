package com.green.chat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

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
@Table(name="FAVORITE_tb")
public class FavoriteListEntity {

  @Id
  private String email;

  @Column(columnDefinition = "varchar(255) default '0'")
  private String food;

  @Column(columnDefinition = "varchar(255) default '0'")
	private String game;

  @Column(columnDefinition = "varchar(255) default '0'")
	private String movie;

  @Column(columnDefinition = "varchar(255) default '0'")
	private String music;

  @Column(columnDefinition = "varchar(255) default '0'")
	private String sports;

  @Column(columnDefinition = "varchar(255) default '0'")
	private String travel;
  
}
