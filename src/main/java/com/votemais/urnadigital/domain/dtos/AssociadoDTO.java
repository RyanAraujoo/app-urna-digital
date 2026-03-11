package com.votemais.urnadigital.domain.dtos;

import com.votemais.urnadigital.domain.Pessoa;
import lombok.Getter;

@Getter
public class AssociadoDTO extends Pessoa {
    private String cpf;

    public AssociadoDTO(String nome, String endereco, Integer idade, String cpf) {
        super(nome, endereco, idade);
        this.cpf = cpf;
    }
}
