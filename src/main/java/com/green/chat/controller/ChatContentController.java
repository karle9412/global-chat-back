package com.green.chat.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ChatContentController {
  public static void main(String args[]) throws Exception {
    List<String> pitches = new ArrayList();

    pitches.add("이거 존나 맛있네");
    pitches.add("그리그의 산왕의 궁전은 진짜 신이다. 존나 노래 개 좋음");
    pitches.add("아 일본 여행 가고 싶다.");
    for (int i = 0; i < pitches.size(); i++) {
      String check = pitches.get(i);
      System.out.println(check);
      ProcessBuilder pb = new ProcessBuilder("python",
          "C:\\Users\\82105\\Desktop\\ws\\chat\\chat\\src\\main\\deeplearning\\test_h5py.py", check);
      Process p = pb.start();
      BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
      System.out.println(br.lines());

      try {
        String line = "";
        while ((line = br.readLine()) != null) {
          System.out.println(line);
        }

      } finally {
        try {
          if (br != null) {
            br.close();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

      }
    }
  }
}
