package com.votemais.urnadigital.domain.records;

import com.votemais.urnadigital.domain.enums.RoleEnum;

public record RegisterDTO(String userName, String password, RoleEnum role, String cpf, String nome, String endereco, int idade) {
}
