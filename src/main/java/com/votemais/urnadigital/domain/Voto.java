package com.votemais.urnadigital.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "tb_voto")
@Setter
public class Voto {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "associado_id")
    private Associado associado;

    public Voto (UUID idPauta, Associado associado) {
        this.setAssociado(associado);
        this.setId(idPauta);
    }

}
