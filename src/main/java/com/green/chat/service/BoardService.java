package com.green.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;
import com.green.chat.dto.BoardDTO;
import com.green.chat.model.BoardEntity;
import com.green.chat.persistence.BoardRepository;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public void write(BoardEntity board) {
        boardRepository.save(board);
    }

    public List<BoardEntity> getBoardList() {
        return boardRepository.findAll();
    }

    public BoardEntity getOneBoardList(String bno) {
        return boardRepository.findByBno(bno);

    }

    public void delete(BoardEntity deleteList) {
        boardRepository.delete(deleteList);
    }

}
