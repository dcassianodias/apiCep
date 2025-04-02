package com.danilodev.apiCep.domain.validators;

import org.springframework.stereotype.Component;

import java.io.ObjectStreamException;
import java.util.regex.Pattern;

@Component
public class CepValidator {

    public void validate(String cep){
        if(!Pattern.matches("\\d{8}", cep)){
            throw new IllegalArgumentException("CEP Inválido");
        }
    }
}
