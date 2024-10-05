package com.example.SinisterBusterDefinitive.service;

import com.example.SinisterBusterDefinitive.dto.ConsultaRequestDTO;
import com.example.SinisterBusterDefinitive.dto.ConsultaResponseDTO;
import com.example.SinisterBusterDefinitive.model.Consulta;
import com.example.SinisterBusterDefinitive.model.Paciente;
import com.example.SinisterBusterDefinitive.model.Dentista;

public class ConsultaMapper {

    // Converter de ConsultaRequestDTO para Consulta (entidade)
    public static Consulta toEntity(ConsultaRequestDTO dto, Paciente paciente, Dentista dentista) {
        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setDentista(dentista);
        consulta.setDataConsulta(dto.dataConsulta());
        consulta.setCustoConsulta(dto.custoConsulta());
        return consulta;
    }

    // Converter de Consulta (entidade) para ConsultaResponseDTO
    public static ConsultaResponseDTO toResponseDTO(Consulta consulta) {
        return new ConsultaResponseDTO(
                consulta.getIdConsulta(),
                consulta.getPaciente().getIdPaciente(),
                consulta.getDentista().getIdDentista(),
                consulta.getDataConsulta(),
                consulta.getCustoConsulta()
        );
    }
}
