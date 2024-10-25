package com.cromxt.user.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String token;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;
}
