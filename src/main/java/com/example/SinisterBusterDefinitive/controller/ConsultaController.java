package com.example.SinisterBusterDefinitive.controller;

import com.example.SinisterBusterDefinitive.dto.ConsultaRequestDTO;
import com.example.SinisterBusterDefinitive.dto.ConsultaResponseDTO;
import com.example.SinisterBusterDefinitive.model.Consulta;
import com.example.SinisterBusterDefinitive.model.Paciente;
import com.example.SinisterBusterDefinitive.model.Dentista;
import com.example.SinisterBusterDefinitive.repository.ConsultaRepository;
import com.example.SinisterBusterDefinitive.repository.PacienteRepository;
import com.example.SinisterBusterDefinitive.repository.DentistaRepository;
import com.example.SinisterBusterDefinitive.service.ConsultaMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/consultas", produces = "application/json")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DentistaRepository dentistaRepository;

    @Autowired
    private ConsultaMapper consultaMapper;

    // POST (criar consulta)
    @Operation(summary = "Cria uma consulta no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro ao criar consulta.")
    })
    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> createConsulta(@Valid @RequestBody ConsultaRequestDTO consultaRequestDTO) {
        Paciente paciente = pacienteRepository.findById(consultaRequestDTO.idPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        Dentista dentista = dentistaRepository.findById(consultaRequestDTO.idDentista())
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));

        Consulta consulta = consultaMapper.requestToConsulta(consultaRequestDTO, paciente, dentista);
        Consulta consultaCriada = consultaRepository.save(consulta);
        ConsultaResponseDTO consultaResponseDTO = consultaMapper.consultaToResponse(consultaCriada);

        return ResponseEntity.ok(consultaResponseDTO);
    }


    // GET (todos as consultas)
    @Operation(summary = "Lista todas as consultas do banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consultas encontradas com sucesso."),
            @ApiResponse(responseCode = "400", description = "Nenhuma consulta encontrada.")
    })
    @GetMapping
    public ResponseEntity<Iterable<ConsultaResponseDTO>> readConsultas() {
        Iterable<Consulta> consultas = consultaRepository.findAll();
        List<ConsultaResponseDTO> consultasResponse = new ArrayList<>();
        consultas.forEach(consulta -> consultasResponse.add(consultaMapper.consultaToResponse(consulta)));
        return ResponseEntity.ok(consultasResponse);
    }


    // GET (por id, consulta específica)
    @Operation(summary = "Busca uma consulta pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta encontrada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Consulta não encontrada.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> getConsultaById(@PathVariable Long id) {
        return consultaRepository.findById(id).map(consulta -> ResponseEntity.ok(consultaMapper.consultaToResponse(consulta)))
                .orElse(ResponseEntity.notFound().build());
    }


    //PUT (atualizar informações de uma consulta cadastrada)
    @Operation(summary = "Atualiza informações de uma consulta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta atualizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar consulta.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> updateConsulta(@PathVariable Long id, @Valid @RequestBody ConsultaRequestDTO consultaRequestDTO) {
        Paciente paciente = pacienteRepository.findById(consultaRequestDTO.idPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        Dentista dentista = dentistaRepository.findById(consultaRequestDTO.idDentista())
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));

        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        consulta.setPaciente(paciente);
        consulta.setDentista(dentista);
        consulta.setDataConsulta(consultaRequestDTO.dataConsulta());
        consulta.setCustoConsulta(consultaRequestDTO.custoConsulta());

        Consulta consultaAtualizada = consultaRepository.save(consulta);
        ConsultaResponseDTO consultaResponseDTO = consultaMapper.consultaToResponse(consultaAtualizada);

        return ResponseEntity.ok(consultaResponseDTO);
    }

    // DELETE (deletar consulta por id)
    @Operation(summary = "Deleta uma consulta pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta deletada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro ao deletar consulta.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        consultaRepository.delete(consulta);
        return ResponseEntity.ok().build();
    }
}