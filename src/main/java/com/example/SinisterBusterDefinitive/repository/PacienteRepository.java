package com.example.SinisterBusterDefinitive.repository;

import com.example.SinisterBusterDefinitive.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
