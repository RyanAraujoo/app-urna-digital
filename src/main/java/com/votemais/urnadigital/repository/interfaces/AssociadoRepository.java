package com.votemais.urnadigital.repository.interfaces;

import com.votemais.urnadigital.domain.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AssociadoRepository extends JpaRepository<Associado, UUID> {}
