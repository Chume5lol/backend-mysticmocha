package com.mysticmocha.mysticmocha.domain;

import com.mysticmocha.mysticmocha.domain.ENUMS.Perfil;
import com.mysticmocha.mysticmocha.domain.ENUMS.Setor;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CLIENTE")
public class Cliente extends Usuario{

    public Cliente(){
        super(true);
        setPerfil(Perfil.CLIENTE);
    }

      public Cliente(Long id, String nome, String sobrenome, String email, String nickname, Setor departamento,
			String cargo, String senha, Perfil perfil, Boolean habilitado){
        super(id, nome, sobrenome, email, nickname, departamento, cargo, senha, Perfil.CLIENTE, true);
        
    }
}
