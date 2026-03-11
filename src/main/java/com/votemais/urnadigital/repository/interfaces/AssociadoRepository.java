package com.votemais.urnadigital.repository.interfaces;

import com.votemais.urnadigital.domain.Associado;
import com.votemais.urnadigital.domain.Voto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AssociadoRepository extends JpaRepository<Associado, UUID> {
        @Modifying
        @Transactional
        @Query("update Associado a set a.votos = :votos where a.id = :id")
        int AtualizarVotosDoAssociado(
                @Param("associado") Voto votos,
                @Param("id") UUID id
        );
}
