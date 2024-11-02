package com.cromxt.user.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "token")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true, nullable = false)
    private String token;

    public Boolean revoked;

    public Boolean expired;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuser_id")
    private UserEntity user;
}
