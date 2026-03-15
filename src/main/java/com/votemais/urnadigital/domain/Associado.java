package com.votemais.urnadigital.domain;

import com.votemais.urnadigital.domain.daos.PautaDAO;
import com.votemais.urnadigital.domain.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "tb_associado")
public class Associado extends Pessoa implements UserDetails {
    @OneToMany(mappedBy = "associado")
    @PrimaryKeyJoinColumn
    private Set<Voto> votos;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private RoleEnum role;

    @Column
    private String CPF;

    private String votar(PautaDAO pauta, Associado associado) {
        Voto voto = new Voto(pauta, associado);
        setVoto(voto);
        return "Voto Registrado para Pauta" + pauta.getNome();
    }

    public void setVoto(Voto voto) {
        this.votos.add(voto);
    }

    public Associado(String cpf,String nome, String endereco, int idade, String password, String login, RoleEnum role) {
        super(nome, endereco, idade);
        this.role = role;
        this.login = login;
        this.password = password;
        this.CPF = cpf;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.getRole().equals(RoleEnum.ADMIN)) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.getLogin();
    }
}
