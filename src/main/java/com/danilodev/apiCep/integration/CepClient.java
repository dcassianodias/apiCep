package com.danilodev.apiCep.integration;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepClient implements CepLookup {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String MOCK_API_URL = "http://localhost:8081/cep/";

    @Override
    public String buscarCep(String cep){
        return restTemplate.getForObject(MOCK_API_URL + cep, String.class);
    }
}
