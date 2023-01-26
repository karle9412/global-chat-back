package com.green.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.chat.model.FavoriteListEntity;
import com.green.chat.persistence.FavoriteRepository;

@Service
public class FavoriteListService {

  @Autowired
    private FavoriteRepository favoriteRepository;

  public void save(FavoriteListEntity favorite) {
    favoriteRepository.save(favorite);

  }

  public FavoriteListEntity getById(String user_email) {
    return favoriteRepository.getById(user_email);
  }

  public FavoriteListEntity getOnefavoriteList(String useremail) {
    return favoriteRepository.getById(useremail);
  }

}
