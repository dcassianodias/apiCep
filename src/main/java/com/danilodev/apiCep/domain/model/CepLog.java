package com.danilodev.apiCep.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "Cep")
@Table(name = "cep_log")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CepLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String responseData;
    private LocalDateTime timestamp;

}
