package com.cromxt.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recovery_details")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecoveryAccountDetails {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private UserEntity user;
    @Enumerated(EnumType.STRING)
    private CountryCode country;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "recovery_email")
    private String recoveryEmail;

}
