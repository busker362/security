package com.project.security.service;

import java.util.concurrent.TimeUnit;

public interface RedisService {

    void saveAuthCode(String id, String authCode, long timeout, TimeUnit unit);

    boolean verifyAuthCode(String id, String code);
}
