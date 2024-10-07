package com.example.SinisterBusterDefinitive.dto;

import com.example.SinisterBusterDefinitive.model.Dentista;
import com.example.SinisterBusterDefinitive.model.Paciente;

public record ConsultaResponseDTO(
        Long idConsulta,
        PacienteResponseDTO paciente,
        DentistaResponseDTO dentista,
        String dataConsulta,
        Double custoConsulta
) {}
