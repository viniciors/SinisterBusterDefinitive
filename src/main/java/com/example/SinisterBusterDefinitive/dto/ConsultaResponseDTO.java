package com.example.SinisterBusterDefinitive.dto;

public record ConsultaResponseDTO(
        Long idConsulta,
        Long idPaciente,
        Long idDentista,
        String dataConsulta,
        Double custoConsulta
) {}
