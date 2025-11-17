package com.project.security.service;

public interface EmailService {

    /**
     * 인증번호 생성 + 이메일 발송
     * @param to 수신 이메일
     * @return 생성된 6자리 인증번호
     */
    String sendAuthCode(String to);

    // 단순 이메일 발송용
    void sendSimpleMessage(String to, String subject, String text);
}
