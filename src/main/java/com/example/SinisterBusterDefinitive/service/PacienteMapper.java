package com.example.SinisterBusterDefinitive.service;

import com.example.SinisterBusterDefinitive.dto.PacienteRequestDTO;
import com.example.SinisterBusterDefinitive.dto.PacienteResponseDTO;
import com.example.SinisterBusterDefinitive.model.Paciente;
import org.springframework.stereotype.Service;

@Service
public class PacienteMapper {


    public Paciente requestToPaciente(PacienteRequestDTO pacienteRequestDTO){
        Paciente paciente = new Paciente();
        paciente.setNome(pacienteRequestDTO.nome());
        paciente.setCpf(pacienteRequestDTO.cpf());
        paciente.setDataNascimento(pacienteRequestDTO.dataNascimento());
        paciente.setPlanoSaude(pacienteRequestDTO.planoSaude());
        paciente.setConsultas(pacienteRequestDTO.consultas());
        return paciente;
    }

    public PacienteResponseDTO pacienteToResponse(Paciente paciente){
        return new PacienteResponseDTO(
                paciente.getIdPaciente(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getDataNascimento(),
                paciente.getPlanoSaude(),
                paciente.getConsultas()
        );
    }
}
