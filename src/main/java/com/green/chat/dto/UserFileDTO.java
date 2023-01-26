package com.green.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserFileDTO {
    
    private String id;
    private String email; 
  private String filename;
  private String fileOriname;
  private String fileUrl;
     
}
