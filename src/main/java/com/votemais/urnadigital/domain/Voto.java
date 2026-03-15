package com.votemais.urnadigital.domain;

import com.votemais.urnadigital.domain.daos.PautaDAO;
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

    @EmbeddedId
    private PautaAssociado votoId;

    @ManyToOne
    @MapsId("associadoID")
    @JoinColumn(name = "associado_id")
    private Associado associado;

    @ManyToOne
    @MapsId("pautaID")
    @JoinColumn(name = "pauta_id")
    private PautaDAO pauta;

    public Voto (PautaDAO pauta, Associado associado) {
        this.setAssociado(associado);
        this.setPauta(pauta);
    }

}
