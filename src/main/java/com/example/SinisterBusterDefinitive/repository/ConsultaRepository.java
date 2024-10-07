package com.example.SinisterBusterDefinitive.repository;

import com.example.SinisterBusterDefinitive.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    // Método que busca todas as consultas associadas a um paciente específico
    List<Consulta> findByPacienteIdPaciente(Long idPaciente);

    // Método que busca todas as consultas associadas a um dentista específico
    List<Consulta> findByDentistaIdDentista(Long idDentista);
}
