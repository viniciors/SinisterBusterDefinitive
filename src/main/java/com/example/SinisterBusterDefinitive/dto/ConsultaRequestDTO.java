package com.example.SinisterBusterDefinitive.dto;

public record ConsultaRequestDTO(
        Long idPaciente,
        Long idDentista,
        String dataConsulta,
        Double custoConsulta
) {
    @Override
    public Long idPaciente() {
        return idPaciente;
    }

    @Override
    public Long idDentista() {
        return idDentista;
    }

    @Override
    public String dataConsulta() {
        return dataConsulta;
    }

    @Override
    public Double custoConsulta() {
        return custoConsulta;
    }
}
