package com.cromxt.user.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "avatar")
public class ProfileAvatar {

    @Id
    @OneToOne
    @JoinColumn(name = "id")
    private UserEntity user;
    private String avatar;
    private String format;
    private String imgSize;
}
