package com.cromxt.userservice.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cromxt.userservice.entity.Tokens;

public interface TokenRepository extends CrudRepository<Tokens, String> {
    
}
