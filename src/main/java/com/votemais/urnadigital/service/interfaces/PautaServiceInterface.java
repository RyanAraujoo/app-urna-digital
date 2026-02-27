package com.votemais.urnadigital.service.interfaces;

import com.votemais.urnadigital.domain.dtos.PautaDTO;
import org.springframework.data.jpa.repository.Query;

public interface PautaServiceInterface {
    String encerrar(String idPauta);

    @Query("UPDATE tb_pauta where id = ? SET statusAbertura = 'ABERTO' ")
    String abrir(String idPauta);

    String cadastrar(PautaDTO pauta);
}
