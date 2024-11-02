package com.cromxt.user.repository;

import com.cromxt.user.entity.Token;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token,String> {

    Optional<Token> findByToken(String token);
}
