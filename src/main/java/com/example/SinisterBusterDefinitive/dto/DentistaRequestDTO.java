package com.example.SinisterBusterDefinitive.dto;

import com.example.SinisterBusterDefinitive.model.Consulta;

import java.util.List;

public record DentistaRequestDTO(
        String nomeDentista,
        String cro,
        String especialidade,
        List<Consulta> consultas
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

    @Override
    public List<Consulta> consultas(){
        return consultas;
    }
}
