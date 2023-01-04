package com.green.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.chat.model.BoardEntity;
import com.green.chat.persistence.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<BoardEntity> getBoardList() {
        return null;
    }

}
