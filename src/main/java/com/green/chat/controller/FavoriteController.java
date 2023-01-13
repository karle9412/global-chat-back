package com.green.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.chat.dto.FavoriteListDTO;
import com.green.chat.dto.FavoriteScoreDTO;
import com.green.chat.model.FavoriteListEntity;
import com.green.chat.service.FavoriteListService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/favoriteList")
public class FavoriteController {

  @Autowired
  FavoriteListService favoriteListService;
  
  @PostMapping("/create")
  public void create(@RequestBody FavoriteListDTO favoriteListDTO){

    FavoriteListEntity favorite = FavoriteListEntity.builder()
    .email(favoriteListDTO.getEmail())
    .build();

    favoriteListService.save(favorite);

  }

  @PutMapping("/food")
    public void food(@AuthenticationPrincipal String user_email, @RequestBody FavoriteScoreDTO favoriteScoreDTO)
    {
        FavoriteListEntity favoriteListEntity = new FavoriteListEntity();
        favoriteListEntity = favoriteListService.getById(user_email);

        int Score = Integer.parseInt(favoriteScoreDTO.getFood());
        int favoriteScore = Integer.parseInt(favoriteListEntity.getFood());

        String resultScore = Integer.toString(Score + favoriteScore);;

        favoriteListEntity.setFood(resultScore);

        favoriteListService.save(favoriteListEntity);
    }

    @PutMapping("/sports")
    public void sports(@AuthenticationPrincipal String user_email, @RequestBody FavoriteScoreDTO favoriteScoreDTO)
    {
        FavoriteListEntity favoriteListEntity = new FavoriteListEntity();
        favoriteListEntity = favoriteListService.getById(user_email);

        int Score = Integer.parseInt(favoriteScoreDTO.getSports());
        int favoriteScore = Integer.parseInt(favoriteListEntity.getSports());

        String resultScore = Integer.toString(Score + favoriteScore);;

        favoriteListEntity.setSports(resultScore);

        favoriteListService.save(favoriteListEntity);
    }

    @PutMapping("/movie")
    public void movie(@AuthenticationPrincipal String user_email, @RequestBody FavoriteScoreDTO favoriteScoreDTO)
    {
        FavoriteListEntity favoriteListEntity = new FavoriteListEntity();
        favoriteListEntity = favoriteListService.getById(user_email);

        int Score = Integer.parseInt(favoriteScoreDTO.getMovie());
        int favoriteScore = Integer.parseInt(favoriteListEntity.getMovie());

        String resultScore = Integer.toString(Score + favoriteScore);;

        favoriteListEntity.setMovie(resultScore);

        favoriteListService.save(favoriteListEntity);
    }

    @PutMapping("/game")
    public void game(@AuthenticationPrincipal String user_email, @RequestBody FavoriteScoreDTO favoriteScoreDTO)
    {
        FavoriteListEntity favoriteListEntity = new FavoriteListEntity();
        favoriteListEntity = favoriteListService.getById(user_email);

        int Score = Integer.parseInt(favoriteScoreDTO.getGame());
        int favoriteScore = Integer.parseInt(favoriteListEntity.getGame());

        String resultScore = Integer.toString(Score + favoriteScore);;

        favoriteListEntity.setGame(resultScore);

        favoriteListService.save(favoriteListEntity);
    }

    @PutMapping("/music")
    public void music(@AuthenticationPrincipal String user_email, @RequestBody FavoriteScoreDTO favoriteScoreDTO)
    {
        FavoriteListEntity favoriteListEntity = new FavoriteListEntity();
        favoriteListEntity = favoriteListService.getById(user_email);

        int Score = Integer.parseInt(favoriteScoreDTO.getMusic());
        int favoriteScore = Integer.parseInt(favoriteListEntity.getMusic());

        String resultScore = Integer.toString(Score + favoriteScore);;

        favoriteListEntity.setMusic(resultScore);

        favoriteListService.save(favoriteListEntity);
    }

    @PutMapping("/travel")
    public void travel(@AuthenticationPrincipal String user_email, @RequestBody FavoriteScoreDTO favoriteScoreDTO)
    {
        FavoriteListEntity favoriteListEntity = new FavoriteListEntity();
        favoriteListEntity = favoriteListService.getById(user_email);

        int Score = Integer.parseInt(favoriteScoreDTO.getTravel());
        int favoriteScore = Integer.parseInt(favoriteListEntity.getTravel());

        String resultScore = Integer.toString(Score + favoriteScore);;

        favoriteListEntity.setTravel(resultScore);

        favoriteListService.save(favoriteListEntity);
    }

    @GetMapping("/view/{useremail}")
    public ResponseEntity<?> favoriteView(@PathVariable("useremail") String useremail){
      FavoriteListEntity favoriteView = favoriteListService.getOnefavoriteList(useremail);
      return ResponseEntity.ok(favoriteView);
    }
  
}
