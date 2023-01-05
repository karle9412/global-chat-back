package com.green.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.chat.model.BoardEntity;
import com.green.chat.service.BoardService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "게시판 리스트")
@Slf4j
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시판 리스트 보기
    @GetMapping("/list")
    public void getBoardList() {
        List<BoardEntity> boardList = boardService.getBoardList();
    }

}
