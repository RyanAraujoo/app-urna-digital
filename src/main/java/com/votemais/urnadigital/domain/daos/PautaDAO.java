package com.votemais.urnadigital.domain.daos;

import com.votemais.urnadigital.domain.enums.StatusAberturaEnum;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "tb_pauta")
@Getter
public class PautaDAO {

    public PautaDAO(String nome,
                         String endereco,
                         int tempoEmMinutos,
                         int quantidadeDeVotosSim,
                         int quantidadeDeVotosNao,
                         StatusAberturaEnum statusAbertura) {

        this.nome = nome;
        this.endereco = endereco;
        this.tempoEmMinutos = tempoEmMinutos;
        this.quantidadeDeVotosSim = quantidadeDeVotosSim;
        this.quantidadeDeVotosNao = quantidadeDeVotosNao;
        this.statusAbertura = statusAbertura;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String nome;

    @Column
    private String endereco;

    @Column
    private int tempoEmMinutos;

    @Column
    private int quantidadeDeVotosSim;

    @Column
    private int quantidadeDeVotosNao;

    @Column
    private StatusAberturaEnum statusAbertura;
}
