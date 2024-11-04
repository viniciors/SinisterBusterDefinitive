package com.example.SinisterBusterDefinitive.dto;

public record SinistroResponseDTO(
        Long idSinistro,
        String tipoSinistro,
        Double valorSinistro,
        String dataSinistro,
        DentistaResponseDTO dentista,
        PacienteResponseDTO paciente
) {
}
