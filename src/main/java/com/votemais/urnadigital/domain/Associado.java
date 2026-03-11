package com.votemais.urnadigital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "tb_associado")
public class Associado extends Pessoa {
    @OneToMany(mappedBy = "associado")
    private Set<Voto> votos;

    private String CPF;

    private String votar(UUID idPauta, Associado associado) {
        Voto voto = new Voto(idPauta, associado);
        setVoto(voto);
        return "Voto Registrado para Pauta" + idPauta;
    }

    public void setVoto(Voto voto) {
        this.votos.add(voto);
    }

    public Associado(String cpf,String nome, String endereco, int idade) {
        super(nome, endereco, idade);
        this.CPF = cpf;
    }
}
