package com.green.chat.controller;

import com.green.chat.dto.ResponseDTO;
import com.green.chat.dto.UserDTO;
import com.green.chat.model.AlarmEntity;
import com.green.chat.model.UserEntity;
import com.green.chat.security.TokenProvider;
import com.green.chat.service.AlarmService;
import com.green.chat.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Api(tags = { "User" })
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AlarmService alarmService;

    // Bean으로 작성해도 됨.
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Operation(summary = "회원가입", description = "user 정보 생성")
    @ApiResponse(code = 200, message = "ok")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            // 리퀘스트를 이용해 저장할 유저 만들기
            UserEntity user = UserEntity.builder()
                    .email(userDTO.getEmail())
                    .username(userDTO.getUsername())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .phonenumber(userDTO.getPhonenumber())
                    .local(userDTO.getLocal())
                    .role(userDTO.getRole())
                    .build();
            // 서비스를 이용해 리파지토리에 유저 저장
            UserEntity registeredUser = userService.create(user);
            UserDTO responseUserDTO = UserDTO.builder()
                    .email(registeredUser.getEmail())
                    // .id(registeredUser.getId())
                    .username(registeredUser.getUsername())
                    .phonenumber(registeredUser.getPhonenumber())
                    .local(registeredUser.getLocal())
                    .role(registeredUser.getRole())
                    .build();

                    // 알람세팅 생성
							AlarmEntity request = AlarmEntity.builder()
							.email(userDTO.getEmail())                                                  
							.build();
                            alarmService.create(request);

                            
            // 유저 정보는 항상 하나이므로 그냥 리스트로 만들어야하는 ResponseDTO를 사용하지 않고 그냥 UserDTO 리턴.
            return ResponseEntity.ok(responseUserDTO);
        } catch (Exception e) {
            // 예외가 나는 경우 bad 리스폰스 리턴.
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
        UserEntity user = userService.getByCredentials(
                userDTO.getEmail(),
                userDTO.getPassword(),
                passwordEncoder);

        if (user != null) {
            // 토큰 생성
            final String token = tokenProvider.create(user);
            final UserDTO responseUserDTO = UserDTO.builder()
                    .email(user.getUsername())
                    // .id(user.getId())
                    .phonenumber(user.getPhonenumber())
                    .local(user.getLocal())
                    .role(userDTO.getRole())
                    .token(token)
                    .build();
            System.out.println("요기요기" + ResponseEntity.ok(responseUserDTO));
            return ResponseEntity.ok().body(responseUserDTO);
        } else {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error("Login failed.")
                    .build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }
    }

    // 전화번호로 이메일 찾기
    @PostMapping("/getemail")
    public ResponseEntity<?> getEmail(@RequestBody UserDTO userDTO) {
        System.out.println("아아아아" + userDTO);
        String email = userService.findByPhonenumber(userDTO.getPhonenumber());
        return ResponseEntity.ok().body(email);
    }

    // 비밀번호 재설정 후 이메일로 보내줌
    @PostMapping("/updatepassword")
    public void updatePassword(@RequestBody UserDTO userDTO) {
        String email = userDTO.getEmail();

        // 랜덤 비밀번호 생성
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String newpasswd = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(newpasswd);

        // 새 비밀번호 저장
        String password = (passwordEncoder.encode(newpasswd));
        UserEntity user = userService.findByEmail(email);
        if (email == null) {
            System.out.println("잘못된 이메일");
            throw new EntityNotFoundException();
        }
        user.setPassword(password);
        userService.save(user);

        // 이메일 발송
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("gmlfyd417@naver.com");
        message.setTo("gmlfyd417@gmail.com");
        message.setSubject("글랜챗 비밀번호 재설정");
        message.setText(newpasswd);
        javaMailSender.send(message);
    }

}