package com.votemais.urnadigital.repository.interfaces;

import com.votemais.urnadigital.domain.daos.PautaDAO;
import com.votemais.urnadigital.domain.dtos.PautaDTO;
import com.votemais.urnadigital.domain.enums.StatusAberturaEnum;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@EnableJpaRepositories
@Repository
public interface PautaRepository extends JpaRepository<PautaDAO, UUID> {

        @Modifying
        @Transactional
        @Query("update PautaDAO p set p.statusAbertura = :status where p.id = :id")
        int AlteraStatusPauta(@Param("id") String idPauta,
                              @Param("status") StatusAberturaEnum status);
}
