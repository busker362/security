package com.project.security.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void saveAuthCode(String id, String authCode, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set("auth:" + id, authCode, timeout, unit);
    }

    @Override
    public boolean verifyAuthCode(String id, String code) {
        String savedCode = redisTemplate.opsForValue().get("auth:" + id);
        return savedCode != null && savedCode.equals(code);
    }
}
