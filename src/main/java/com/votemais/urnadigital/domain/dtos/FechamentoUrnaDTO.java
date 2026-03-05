package com.votemais.urnadigital.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FechamentoUrnaDTO {
    private int quantTotalSim;
    private int quantTotalNao;

    public String returnFechamento() {
        return "Sim: " + this.getQuantTotalSim() + " || Não: " + this.getQuantTotalNao();
    }
}
