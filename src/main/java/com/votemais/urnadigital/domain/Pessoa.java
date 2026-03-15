package com.votemais.urnadigital.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@MappedSuperclass
@Getter
@NoArgsConstructor
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String nome;

    @Column
    private String endereco;

    @Column
    private Integer idade;


    public Pessoa(String nome, String endereco, int idade) {
       setEndereco(endereco);
       setIdade(idade);
       setNome(nome);
    }
}