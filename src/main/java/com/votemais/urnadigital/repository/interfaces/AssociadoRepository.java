package com.votemais.urnadigital.repository.interfaces;

import com.votemais.urnadigital.domain.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface AssociadoRepository extends JpaRepository<Associado, UUID> {
    UserDetails findByLogin(String login);
}
