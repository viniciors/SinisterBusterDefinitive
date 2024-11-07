package com.example.SinisterBusterDefinitive.dto;

import com.example.SinisterBusterDefinitive.model.Plano;


public record PacienteRequestDTO(
        String nome,
        String cpf,
        String dataNascimento,
        Plano planoSaude

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

}
