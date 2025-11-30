package com.mysticmocha.mysticmocha.domain;

import com.mysticmocha.mysticmocha.domain.ENUMS.Perfil;
import com.mysticmocha.mysticmocha.domain.ENUMS.Setor;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PRESTADOR")
public class Prestador extends Usuario{

    
    public Prestador(){
        super(true);
        setPerfil(Perfil.PRESTADOR);
    }

      public Prestador(Long id, String nome, String sobrenome, String email, String nickname, Setor departamento,
			String cargo, String senha, Perfil perfil, Boolean habilitado){
        super(id, nome, sobrenome, email, nickname, departamento, cargo, senha, Perfil.PRESTADOR, true);
        
    }
}
