package com.danilodev.apiCep.domain.service;

import com.danilodev.apiCep.domain.dto.CepResponseDTO;
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

    public CepResponseDTO buscarCep(String cep) {
        if (!isValidCep(cep)) {
            throw new IllegalArgumentException("CEP Inválido");
        }

        String response = resTemplate.getForObject(MOCK_API_URL + cep, String.class);
        LocalDateTime timestamp = LocalDateTime.now();

        CepLog log = new CepLog(null, cep, response, timestamp);
        cepLogRepository.save(log);

        return new CepResponseDTO(cep, response, timestamp);
    }

    private boolean isValidCep(String cep){
        return Pattern.matches("\\d{8}", cep);
    }
}
