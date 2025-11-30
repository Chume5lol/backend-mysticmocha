package com.mysticmocha.mysticmocha.domain;

import com.mysticmocha.mysticmocha.domain.ENUMS.Perfil;
import com.mysticmocha.mysticmocha.domain.ENUMS.Setor;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMINISTRADOR")
public class Administrador extends Usuario{

    public Administrador(){
        super(true);
        setPerfil(Perfil.ADMINISTRADOR);
    }

      public Administrador(Long id, String nome, String sobrenome, String email, String nickname, Setor departamento,
			String cargo, String senha, Perfil perfil, Boolean habilitado){
        super(id, nome, sobrenome, email, nickname, departamento, cargo, senha, Perfil.ADMINISTRADOR, true);
        
    }
}
