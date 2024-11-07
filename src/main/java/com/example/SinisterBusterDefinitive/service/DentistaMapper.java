package com.example.SinisterBusterDefinitive.service;

import com.example.SinisterBusterDefinitive.dto.DentistaRequestDTO;
import com.example.SinisterBusterDefinitive.dto.DentistaResponseDTO;
import com.example.SinisterBusterDefinitive.model.Dentista;
import org.springframework.stereotype.Service;

@Service
public class DentistaMapper {

    public Dentista requestToDentista(DentistaRequestDTO dentistaRequestDTO) {
        Dentista dentista = new Dentista();
        dentista.setNomeDentista(dentistaRequestDTO.nomeDentista());
        dentista.setCro(dentistaRequestDTO.cro());
        dentista.setEspecialidade(dentistaRequestDTO.especialidade());
        return dentista;
    }

    public DentistaResponseDTO dentistaToResponse(Dentista dentista) {
        return new DentistaResponseDTO(
            dentista.getIdDentista(),
            dentista.getNomeDentista(),
            dentista.getCro(),
            dentista.getEspecialidade()
        );
    }
}
