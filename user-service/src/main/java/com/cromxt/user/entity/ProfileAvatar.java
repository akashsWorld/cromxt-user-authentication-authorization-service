package com.cromxt.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "avatar")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProfileAvatar implements Serializable{
    @Serial
    private static final long serialVersionUID = 29675678654762172L;

    @Id
    @Column(name = "cuser_id")
    private String id;

//    UserEntity id is used here as primary key so here create a OneToOne Relationship.
    @OneToOne
    @MapsId
    @JoinColumn(name = "cuser_id")
    private UserEntity user;
    private String url;
    private String format;
    private String imgSize;
}
