package com.votemais.urnadigital.domain.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PautaDTO {
    private String nome;
    private String endereco;
    private int tempoEmMinutos;
}
