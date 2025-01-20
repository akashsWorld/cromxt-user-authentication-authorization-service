package com.cromxt.userservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.cromxt.userservice.entity.CromUser;

public interface CromUserRepository extends CrudRepository<CromUser, String> {
    CromUser findByEmail(String email);

}
