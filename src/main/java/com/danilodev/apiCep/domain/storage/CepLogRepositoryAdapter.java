package com.danilodev.apiCep.domain.storage;

import com.danilodev.apiCep.domain.model.CepLog;
import com.danilodev.apiCep.domain.model.CepLogStorage;
import com.danilodev.apiCep.domain.repository.CepLogRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CepLogRepositoryAdapter  implements CepLogStorage {

    private final CepLogRepository repository;

    public CepLogRepositoryAdapter(CepLogRepository repository){
        this.repository = repository;
    }

    @Override
    public void salvar(CepLog log) {
        repository.save(log);
    }

}
