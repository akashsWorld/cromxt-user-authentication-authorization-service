package com.cromxt.user.repository;

import com.cromxt.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, String> {
    Boolean existsByUsername(String email);

    Optional<UserEntity> findByUsername(String email);
}
