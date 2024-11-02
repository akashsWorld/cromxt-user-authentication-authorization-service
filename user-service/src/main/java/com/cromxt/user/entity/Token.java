package com.cromxt.user.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "token")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Token implements Serializable {
    @Serial
    private static final long serialVersionUID = 19271600237443477L;

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
