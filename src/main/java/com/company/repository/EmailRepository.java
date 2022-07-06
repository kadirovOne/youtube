package com.company.repository;

import com.company.entity.EmailHistoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<EmailHistoryEntity,Integer> {
}
