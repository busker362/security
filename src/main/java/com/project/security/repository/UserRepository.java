package com.project.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.security.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    //로그인 시 id로 조회 
    Optional<User> findById(String id);
    // User findById(String id) 과 의 차이
    // => 반환 타입이 User임 Null값이 반환 될 수 있음 예외처리를 직접해줘야함
    //값이 없다면 Null값이 아닌 Empty가 반환됨
    
    Optional<User> findByEmail(String email);

}
