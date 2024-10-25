package com.cromxt.user.repository;

import com.cromxt.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {
}
