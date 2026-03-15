package com.votemais.urnadigital.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class PautaAssociado implements Serializable {

    @Column(name = "associado_id")
    private String associadoID;

    @Column(name = "pauta_id")
    private String pautaID;

}
