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
@Table(name = "File_TB")
@DynamicInsert
@Getter
@Setter
public class FileEntity {

  @Id
  private Number filNumber;

  @Column(length = 100, nullable = false)
  private String fileName;

  @Column(length = 10, nullable = false)
  private String fileExt;

  @Column(length = 3000, nullable = false)
  private String sFileName;

  private LocalDate indate = LocalDate.now();
  
}
