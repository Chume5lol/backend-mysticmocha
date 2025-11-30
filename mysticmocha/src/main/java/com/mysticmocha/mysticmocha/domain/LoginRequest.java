package com.mysticmocha.mysticmocha.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String nickname;
    private String senha;

}
