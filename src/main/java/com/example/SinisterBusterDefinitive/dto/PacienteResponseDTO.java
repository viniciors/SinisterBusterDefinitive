package com.example.SinisterBusterDefinitive.dto;

import com.example.SinisterBusterDefinitive.model.Consulta;
import com.example.SinisterBusterDefinitive.model.Plano;

import java.util.List;

public record PacienteResponseDTO(
        Long idPaciente,
        String nome,
        String cpf,
        String dataNascimento,
        Plano planoSaude,
        List<Consulta> consultas
) {}
