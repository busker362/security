package com.project.security.service;

import java.util.concurrent.TimeUnit;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import com.project.security.dto.LoginRequestDto;
import com.project.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final RedisService redisService;

    public void login(LoginRequestDto request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getId(), request.getPwd())
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("아이디 또는 비밀번호 불일치");
        }

        String authCode = emailService.sendAuthCode(
                userRepository.findById(request.getId())
                              .orElseThrow(() -> new RuntimeException("사용자 없음"))
                              .getEmail()
        );

        redisService.saveAuthCode(request.getId(), authCode, 3, TimeUnit.MINUTES);
    }


}
