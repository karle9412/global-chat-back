package com.green.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.chat.dto.FriendListDTO;
import com.green.chat.model.FriendListEntity;
import com.green.chat.model.UserEntity;
import com.green.chat.service.FriendListService;
import com.google.common.util.concurrent.Service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("/friendlist")
public class FriendListController {

    @Autowired
    private FriendListService friendListService;

    // check = 0:요청보냄, 1:요청받음, 2:친구, 3:차단
    // 친구요청
    @PostMapping("/request")
    public void apply(@AuthenticationPrincipal String user_email, @RequestBody FriendListDTO friendListDTO) {

        FriendListEntity apply = FriendListEntity.builder()
                .email(user_email)
                .requireemail(friendListDTO.getRequireemail())
                .requirecheck("0")
                .build();
        friendListService.create(apply);

        // 요청받음
        FriendListEntity require = FriendListEntity.builder()
                .email(friendListDTO.getRequireemail())
                .requireemail(user_email)
                .requirecheck("1")
                .build();
        friendListService.create(require);
    }

    // 보낸 요청 목록 보기
    @GetMapping("/request")
    public ResponseEntity<?> getRequestList(@AuthenticationPrincipal String user_email) {

        List<FriendListEntity> entities = friendListService.getRequestList(user_email);

        return ResponseEntity.ok(entities);
    }

    // 받은 요청 목록 보기
    @GetMapping("/require")
    public ResponseEntity<?> getRequireList(@AuthenticationPrincipal String user_email) {

        List<FriendListEntity> entities = friendListService.getRequireList(user_email);

        return ResponseEntity.ok(entities);
    }

    // 친구승낙
    @PutMapping("/consent")
    public void applyfriend(@AuthenticationPrincipal String user_email, @RequestParam String oppemail) {

        FriendListEntity list = new FriendListEntity();
        list = friendListService.findByEmailAndRequireemail(user_email, oppemail);
        System.out.println("리스트" + list);

        FriendListEntity list2 = new FriendListEntity();
        list2 = friendListService.findByEmailAndRequireemail(oppemail, user_email);
        System.out.println("리스트2" + list2);

        list.setRequirecheck("2");
        list2.setRequirecheck("2");
        friendListService.save(list);
        friendListService.save(list2);
    }

    // 친구 목록 보기
    @GetMapping("/consent")
    public ResponseEntity<?> getFriendList(@AuthenticationPrincipal String user_email) {

        List<FriendListEntity> entities = friendListService.friendlist(user_email);

        return ResponseEntity.ok(entities);
    }

    // 차단
    @PutMapping("/block")
    public void blockfriend(@AuthenticationPrincipal String user_email, @RequestParam String oppemail) {

        FriendListEntity list = new FriendListEntity();
        list = friendListService.findByEmailAndRequireemail(user_email, oppemail);
        System.out.println("리스트" + list);

        FriendListEntity list2 = new FriendListEntity();
        list2 = friendListService.findByEmailAndRequireemail(oppemail, user_email);
        System.out.println("리스트2" + list2);

        list.setRequirecheck("3");
        list2.setRequirecheck("3");
        friendListService.save(list);
        friendListService.save(list2);
    }

    // 차단 목록 보기
    @GetMapping("/block")
    public ResponseEntity<?> getBlock(@AuthenticationPrincipal String user_email) {

        List<FriendListEntity> entities = friendListService.blocklist(user_email);

        return ResponseEntity.ok(entities);
    }

    // 차단풀기, 친구삭제
    @DeleteMapping("/block")
    public void blockCancle(@AuthenticationPrincipal String user_email, @RequestParam String oppemail) {

        FriendListEntity list = new FriendListEntity();
        list = friendListService.findByEmailAndRequireemail(user_email, oppemail);
        System.out.println("리스트" + list);

        FriendListEntity list2 = new FriendListEntity();
        list2 = friendListService.findByEmailAndRequireemail(oppemail, user_email);
        System.out.println("리스트2" + list2);

        list.setRequirecheck("3");
        list2.setRequirecheck("3");
        friendListService.delete(list);
        friendListService.delete(list2);

    }

}
