package com.cromxt.userservice.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cromxt.userservice.entity.CromUser;


public interface CromUserRepository extends CrudRepository<CromUser, String> {
    
    Optional<CromUser> findByUsername(String username);
}
