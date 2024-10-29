package com.cromxt.user.repository;

import com.cromxt.user.dtos.requests.RecoveryAccountDetailsDTO;
import com.cromxt.user.entity.RecoveryAccountDetails;
import com.cromxt.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface RecoveryAccountDetailsRepository extends CrudRepository<RecoveryAccountDetails, UserEntity> {
}
