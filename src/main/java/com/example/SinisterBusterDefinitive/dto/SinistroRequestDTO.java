package com.example.SinisterBusterDefinitive.dto;

public record SinistroRequestDTO(
        String tipoSinistro,
        Double valorSinistro,
        String dataSinistro,
        Long idPaciente,
        Long idDentista
) {
}
