package com.votemais.urnadigital.domain;

import com.votemais.urnadigital.domain.enums.StatusAberturaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static com.votemais.urnadigital.domain.enums.StatusAberturaEnum.FECHADO;

@Getter
@Setter
@AllArgsConstructor
public class Pauta {

    private UUID id = UUID.randomUUID();
    private String nome;
    private String endereco;
    private int tempoEmMinutos;
    private int quantidadeDeVotosSim = 0;
    private int quantidadeDeVotosNao = 0;
    private StatusAberturaEnum statusAbertura = FECHADO;
}
