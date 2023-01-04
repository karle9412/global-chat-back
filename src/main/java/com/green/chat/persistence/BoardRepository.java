package com.green.chat.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.chat.model.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, String> {

}
