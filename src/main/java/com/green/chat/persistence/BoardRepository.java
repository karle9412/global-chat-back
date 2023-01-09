package com.green.chat.persistence;

import org.springframework.stereotype.Repository;

import com.green.chat.model.BoardEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, String> {

    BoardEntity findByBno(String bno);

    BoardEntity getByBno(String bno);

}
