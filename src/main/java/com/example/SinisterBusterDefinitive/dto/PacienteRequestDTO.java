package com.example.SinisterBusterDefinitive.dto;

import com.example.SinisterBusterDefinitive.model.Consulta;
import com.example.SinisterBusterDefinitive.model.Plano;

import java.util.List;


public record PacienteRequestDTO(
        String nome,
        String cpf,
        String dataNascimento,
        Plano planoSaude,
        List<Consulta> consultas


) {
    @Override
    public String nome() {
        return nome;
    }

    @Override
    public String cpf() {
        return cpf;
    }

    @Override
    public String dataNascimento() {
        return dataNascimento;
    }

    @Override
    public Plano planoSaude() {
        return planoSaude;
    }

    @Override
    public List<Consulta> consultas() {
        return consultas;
    }
}
