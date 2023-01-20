package com.green.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.chat.dto.BoardDTO;
import com.green.chat.dto.PageDTO;
import com.green.chat.dto.ResponseDTO;
import com.green.chat.model.BoardEntity;
import com.green.chat.model.UserEntity;
import com.green.chat.service.BoardService;
import com.green.chat.service.UserService;


import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    // 게시글 작성
    @PostMapping("/write")
    public void write(@AuthenticationPrincipal String email, @RequestBody BoardDTO boardDTO) {
        System.out.println("dd" + boardDTO);

        UserEntity user = userService.findByEmail(email);
        String boardWriter = user.getUsername();

        BoardEntity board = BoardEntity.builder()
                .email(email)
                .boardContent(boardDTO.getBoardContent())
                .boardWriter(boardWriter)
                .build();
        boardService.write(board);
    }

    // 게시글 리스트 보기
  
    @GetMapping("/list/{num}")
    public ResponseEntity<?> boardList(@PathVariable("num") int num) {
        
        int postnum = 10;

        // List<BoardEntity> boardList = boardService.getBoardList();
        List<BoardEntity> boardList = boardService.getBoardListpage(postnum,num);

        if(boardList.size() == 0) {
            ResponseDTO responseDTO = ResponseDTO.builder().error("").build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        } else {
            return ResponseEntity.ok(boardList);
        }

    }

    @GetMapping("/search/{searchItem}/{num}")
    public ResponseEntity<?> search(@PathVariable("searchItem") String searchItem, @PathVariable("num") int num) {

        int postnum = 10;

        List<BoardEntity> searchList = boardService.search(postnum,num,searchItem);

        return ResponseEntity.ok(searchList);
    }

    // 게시글 검색
    @GetMapping("/search/{searchItem}")
    public ResponseEntity<?> search(@PathVariable("searchItem") String searchItem) {

        List<BoardEntity> searchList = boardService.search(searchItem);

        return ResponseEntity.ok(searchList);
    }

    
    // 게시글 상세보기
  
    @GetMapping("/detail/{bno}")
    public ResponseEntity<?> boardDetail(@PathVariable("bno") String bno) {

        BoardEntity boardDetail = boardService.getOneBoardList(bno);

        return ResponseEntity.ok(boardDetail);
    }

    // 게시글 수정
    @PutMapping("/update/{bno}")
    public void update(@PathVariable("bno") String bno, @RequestBody BoardDTO boardDTO) {

        BoardEntity updateList = new BoardEntity();
        updateList = boardService.getOneBoardList(bno);
        updateList.setBoardContent(boardDTO.getBoardContent());

        boardService.write(updateList);
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{bno}")
    public void delete(@PathVariable("bno") String bno) {

        BoardEntity deleteList = new BoardEntity();
        deleteList = boardService.getOneBoardList(bno);

        boardService.delete(deleteList);
    }

    // 게시글 검색
    // @GetMapping("/search")
    // public ResponseEntity<?> search(@RequestParam("keyword") String keyword) {

    // List<BoardEntity> searchList = boardService.search(keyword);

    // return ResponseEntity.ok(searchList);
    // }

}
