package com.green.chat.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Board_tb")
public class BoardEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private int fileNumber;

    @Column(nullable = false)
    private int boardNumber;

    @Column(nullable = false)
    private String boardTitle;

    @Column(nullable = false)
    @Lob
    private String boardContent;

    @Column(nullable = false)
    @CreatedDate
    private LocalDate boardDate;

    @Column(nullable = false)
    private String boardCategory;

    @Column(nullable = true, length = 500)
    private String boardHashTag;

    @Column(nullable = true)
    private int boardLike;

}
