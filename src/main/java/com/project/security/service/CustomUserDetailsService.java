package com.project.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.security.repository.UserRepository;
import com.project.security.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username)
                                  .orElseThrow(() -> new UsernameNotFoundException("사용자 없음"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getId())
                .password(user.getPwd()) // 이미 암호화되어 있어야 함
                .roles("USER")
                .build();
    }
}
