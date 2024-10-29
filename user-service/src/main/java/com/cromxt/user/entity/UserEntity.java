package com.cromxt.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "cuser")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Email
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,name = "first_name")
    private String firstName;
    @Column(nullable = false,name = "last_name")
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(nullable = false,updatable = false)
    private Date birthdate;
//    @OneToMany(mappedBy = "user")
//    private Set<Token> token;
    @CreationTimestamp
    @Column(nullable = false,name = "created_on",updatable = false)
    private Timestamp createdOn;
    @UpdateTimestamp
    @Column(nullable = false,name = "updated_on")
    private Timestamp updatedOn;
}
