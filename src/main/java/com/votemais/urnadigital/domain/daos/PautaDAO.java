package com.votemais.urnadigital.domain.daos;

import com.votemais.urnadigital.domain.enums.StatusAberturaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.UUID;

import static com.votemais.urnadigital.domain.enums.StatusAberturaEnum.FECHADO;

@Entity
@Table(name = "tb_pauta")
@AllArgsConstructor
public class PautaDAO {

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
