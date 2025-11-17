package com.project.security.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {
    
    @Id
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seq;

    @Getter
    @Column(name = "id", unique = true)
    private String id;

    @Getter
    @Column(name = "pwd")
    private String pwd;

    @Getter
    @Column(name = "email", unique = true)
    private String email;

    @CreatedDate
    @Column(name = "insert_dtm", updatable = false)
    private LocalDateTime insertDtm;

    @LastModifiedDate
    @Column(name = "update_dtm")
    private LocalDateTime updateDtm;

    @Builder
    public User(String id, String pwd, String email){
        this.id = id;
        this.pwd = pwd;
        this.email = email;
    }

}
