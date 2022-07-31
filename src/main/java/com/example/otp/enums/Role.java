package com.example.otp.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CLIENT("Client"), ADMIN("Administrator");

    private String descricao;

    Role(String descricao){
        this.descricao=descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
