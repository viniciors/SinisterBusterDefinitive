package com.example.SinisterBusterDefinitive.dto;


public record DentistaRequestDTO(
        String nomeDentista,
        String cro,
        String especialidade
) {
    @Override
    public String nomeDentista() {
        return nomeDentista;
    }

    @Override
    public String cro() {
        return cro;
    }

    @Override
    public String especialidade() {
        return especialidade;
    }
}
