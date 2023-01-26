package com.green.chat.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.chat.model.FavoriteListEntity;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteListEntity,String>{
}
