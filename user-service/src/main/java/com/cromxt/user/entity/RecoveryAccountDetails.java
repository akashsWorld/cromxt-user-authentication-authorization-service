package com.cromxt.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "recovery_details")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecoveryAccountDetails implements Serializable {
    @Serial
    private static final long serialVersionUID = 3226859728349059884L;
    @Id
    @Column(name="cuser_id")
    private String id;
//    UserEntity id is used here as primary key so here create a OneToOne Relationship.

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "cuser_id")
    private UserEntity user;
    @Enumerated(EnumType.STRING)
    private CountryCode country;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "recovery_email")
    private String recoveryEmail;

}
