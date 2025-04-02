package com.danilodev.apiCep.controller;

import com.danilodev.apiCep.domain.dto.CepResponseDTO;
import com.danilodev.apiCep.domain.service.CepService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cep")
public class CepController {

    private final CepService service;

    public CepController(CepService service) {
        this.service = service;
    }

    @GetMapping("/{cep}")
    public CepResponseDTO buscarCep(@PathVariable String cep){
        return service.buscarCep(cep);
    }
}
