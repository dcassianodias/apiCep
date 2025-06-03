package com.danilodev.apiCep.domain.service;

import com.danilodev.apiCep.domain.dto.CepResponseDTO;
import com.danilodev.apiCep.domain.model.CepLog;
import com.danilodev.apiCep.domain.model.CepLogStorage;
import com.danilodev.apiCep.domain.validators.CepValidator;
import com.danilodev.apiCep.integration.CepLookup;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CepService {

    private final CepLookup cepClient;
    private final CepValidator cepValidator;
    private final CepLogStorage cepLogStorage;

    public CepService(CepLookup cepClient, CepValidator cepValidator, CepLogStorage cepLogStorage) {
        this.cepClient = cepClient;
        this.cepValidator = cepValidator;
        this.cepLogStorage = cepLogStorage;
    }

    @Cacheable(value = "cep_search", key = "#cep")
    public CepResponseDTO buscarCep(String cep) {
        cepValidator.validate(cep);
        String response = cepClient.buscarCep(cep);

        LocalDateTime timestamp = LocalDateTime.now();
        CepLog log = new CepLog(null, cep, response, timestamp);
        cepLogStorage.salvar(log);

        return new CepResponseDTO(cep, response, timestamp);
    }
}
