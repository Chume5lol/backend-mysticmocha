package com.mysticmocha.mysticmocha.domain;

import com.mysticmocha.mysticmocha.domain.ENUMS.Perfil;
import com.mysticmocha.mysticmocha.domain.ENUMS.Setor;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("GESTOR")
public class Gestor extends Usuario{

    
    public Gestor(){
        super(true);
        setPerfil(Perfil.GESTOR);
    }

      public Gestor(Long id, String nome, String sobrenome, String email, String nickname, Setor departamento,
			String cargo, String senha, Perfil perfil, Boolean habilitado){
        super(id, nome, sobrenome, email, nickname, departamento, cargo, senha, Perfil.GESTOR, true);
        
    }
}
