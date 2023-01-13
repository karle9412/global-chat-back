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
import com.green.chat.model.BoardEntity;
import com.green.chat.model.UserEntity;
import com.green.chat.service.BoardService;
import com.green.chat.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;

@Api(tags = { "Board" })
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    // 게시글 작성
    @Operation(summary = "게시글 작성", description = "board 정보 생성")
    @ApiResponse(code = 200, message = "ok")
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
    @Operation(summary = "게시글 리스트 보기", description = "board 정보 리스트")
    @ApiResponse(code = 200, message = "ok")
    @GetMapping("/list")
    public ResponseEntity<?> boardList() {

        List<BoardEntity> boardList = boardService.getBoardList();

        return ResponseEntity.ok(boardList);
    }

    // 게시글 상세보기
    @Operation(summary = "게시글 상세보기", description = "board 정보 상세보기")
    @ApiResponse(code = 200, message = "ok")
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
}
