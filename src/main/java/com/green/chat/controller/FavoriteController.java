package com.green.chat.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.chat.dto.FavoriteCreateDto;
import com.green.chat.dto.FavoriteScoreDTO;
import com.green.chat.model.FavoriteListEntity;
import com.green.chat.service.FavoriteListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/favoriteList")
public class FavoriteController {

    @Autowired
    FavoriteListService favoriteListService;

    @PutMapping("/algorithm")
    public void algorithm(@AuthenticationPrincipal String email) {
        ProcessBuilder builder;
        BufferedReader br;

        String arg1 = "C:/Users/82105/AppData/Local/Programs/Python/Python38/python.exe";
        String arg2 = "C:/Users/82105/Desktop/ws/chat/chat/src/main/deeplearning/deeplearning_result.py";

        builder = new ProcessBuilder(arg1, arg2, email);

        builder.redirectErrorStream(true);
        try {
            Process process = builder.start();

            int exitval = process.waitFor();

            ArrayList<String> al = new ArrayList<>();

            br = new BufferedReader(new InputStreamReader(process.getInputStream(), "euc-kr"));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @PostMapping("/create")
    public void create(@RequestBody FavoriteScoreDTO favoriteScoreDTO) {
        System.out.println("나와라");
        System.out.println(favoriteScoreDTO.getEmail());
        FavoriteListEntity favoriteList = FavoriteListEntity.builder()
        .email(favoriteScoreDTO.getEmail())
        .build();

        favoriteListService.save(favoriteList);
    }

    @PutMapping("/plus")
    public void plus(@RequestBody FavoriteCreateDto favoriteCreateDto, @RequestParam String email){
        for(int i = 0; i <= 2; i++){
            String text = "";
            switch(i){
                case 0: text = favoriteCreateDto.getA();
                break;
                case 1: text = favoriteCreateDto.getB();
                break;
                case 2: text = favoriteCreateDto.getC();
                break;
            }

            switch(text){
                case "game" : favoriteListService.update_game(email);
                break;
                case "music" : favoriteListService.update_music(email);
                break;
                case "movie" : favoriteListService.update_movie(email);
                break;
                case "travel" : favoriteListService.update_travel(email);
                break;
                case "sports" : favoriteListService.update_sports(email);
                break;
                case "food" : favoriteListService.update_food(email);
            }
        }
    }

    @GetMapping("/view/{useremail}")
    public ResponseEntity<?> favoriteView(@PathVariable("useremail") String useremail) {
        FavoriteListEntity favoriteView = favoriteListService.getOnefavoriteList(useremail);
        return ResponseEntity.ok(favoriteView);
    }

}
