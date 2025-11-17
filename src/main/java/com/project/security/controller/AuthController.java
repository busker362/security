package com.project.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.project.security.dto.LoginRequestDto;
import com.project.security.dto.VerifyRequestDto;
import com.project.security.service.AuthService;
import com.project.security.service.RedisService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final RedisService redisService;

    @GetMapping("/")
    public String home() {
        return "서버 정상 실행 중!";
    }

    // 로그인 -> 인증번호(이메일) 발송
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto request) {
        authService.login(request); // 내부에서 인증 + 이메일 발송 + Redis 저장
        return ResponseEntity.ok("인증번호가 이메일로 발송되었습니다.");
    }

    // 인증번호 검증 (JSON body로 받음)
    @PostMapping("/verify")
    public ResponseEntity<String> verifyAuthCode(@RequestBody VerifyRequestDto dto) {
        boolean result = redisService.verifyAuthCode(dto.getId(), dto.getCode());
        if (result) {
            // 성공 시 필요한 후속 작업(예: 세션 생성, JWT 발급 등)을 여기서 호출할 수 있음
            return ResponseEntity.ok("인증 성공!");
        } else {
            return ResponseEntity.status(400).body("인증 실패 또는 인증번호 만료됨");
        }
    }
}
