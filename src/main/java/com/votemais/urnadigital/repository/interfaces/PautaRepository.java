package com.votemais.urnadigital.repository.interfaces;

import com.votemais.urnadigital.domain.daos.PautaDAO;
import com.votemais.urnadigital.domain.dtos.PautaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@EnableJpaRepositories
@Repository
public interface PautaRepository extends JpaRepository<PautaDAO, UUID> {
        String encerrar(String idPauta);
        String abrir(String idPauta);
        String cadastrar(PautaDTO pauta);
}
