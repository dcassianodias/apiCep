package com.danilodev.apiCep.domain.repository;

import com.danilodev.apiCep.domain.model.CepLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CepLogRepository extends JpaRepository<CepLog, Long> {
}
