package com.green.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BoardDTO {
    private String id;
    private String title;
    private String content;
    private String writer;
    private String date;
}
