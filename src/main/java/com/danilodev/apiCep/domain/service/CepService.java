package com.danilodev.apiCep.domain.service;

import com.danilodev.apiCep.domain.model.CepLog;
import com.danilodev.apiCep.domain.repository.CepLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Service
public class CepService {


    private final CepLogRepository cepLogRepository;
    private final RestTemplate resTemplate = new RestTemplate();
    private final String MOCK_API_URL = "http://localhost:8081/cep/";

    public CepService(CepLogRepository cepLogRepository) {
        this.cepLogRepository = cepLogRepository;
    }

    public String buscarCep(String cep){
        if (!isValidCep(cep)){
            throw new IllegalArgumentException("CEP Inválido");
        }

        String response = resTemplate.getForObject(MOCK_API_URL + cep, String.class);

        CepLog log = new CepLog();
        log.setCep(cep);
        log.setResponseData(response);
        log.setTimestamp(LocalDateTime.now());
        cepLogRepository.save(log);

        return response;
    }

    private boolean isValidCep(String cep){
        return Pattern.matches("\\d{8}", cep);
    }
}
