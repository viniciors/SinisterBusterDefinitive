package com.example.SinisterBusterDefinitive.controller;

import com.example.SinisterBusterDefinitive.dto.ConsultaResponseDTO;
import com.example.SinisterBusterDefinitive.dto.PacienteRequestDTO;
import com.example.SinisterBusterDefinitive.dto.PacienteResponseDTO;
import com.example.SinisterBusterDefinitive.model.Consulta;
import com.example.SinisterBusterDefinitive.model.Paciente;
import com.example.SinisterBusterDefinitive.repository.ConsultaRepository;
import com.example.SinisterBusterDefinitive.repository.PacienteRepository;
import com.example.SinisterBusterDefinitive.service.ConsultaMapper;
import com.example.SinisterBusterDefinitive.service.PacienteMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
// localhost:8080/pacientes
@RequestMapping(value = "/pacientes", produces = "application/json")
@Tag(name = "api-pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private PacienteMapper pacienteMapper;
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private ConsultaMapper consultaMapper;

    //POST (criar paciente)
    @Operation(summary = "Cria um paciente e grava no banco.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Paciente cadastrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos.",
            content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> createPaciente(@Valid @RequestBody PacienteRequestDTO pacienteRequestDTO) {
        Paciente paciente = pacienteMapper.requestToPaciente(pacienteRequestDTO);
        Paciente pacienteCriado = pacienteRepository.save(paciente);
        PacienteResponseDTO pacienteResponseDTO = pacienteMapper.pacienteToResponse(pacienteCriado);
        return new ResponseEntity<>(pacienteResponseDTO, HttpStatus.CREATED);
    }

    // GET (todos os pacientes)
    @Operation(summary = "Busca todos os pacientes cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado com sucesso."),
            @ApiResponse(responseCode = "204", description = "Nenhum paciente encontrado."),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado.",
            content = @Content(schema = @Schema()))
    })
    @GetMapping
    public ResponseEntity<PacienteResponseDTO> getAllPacientes() {
        Iterable<Paciente> pacientes = pacienteRepository.findAll();
        List<PacienteResponseDTO> pacientesResponse = new ArrayList<>();
        pacientes.forEach(paciente -> pacientesResponse.add(pacienteMapper.pacienteToResponse(paciente)));
        return new ResponseEntity(pacientesResponse, HttpStatus.OK);
    }

    // GET (por id, paciente específico)
    @Operation(summary = "Busca um paciente pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado.",
            content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> getPacienteById(@PathVariable Long id) {
        return pacienteRepository.findById(id)
                .map(paciente -> ResponseEntity.ok(pacienteMapper.pacienteToResponse(paciente)))
                .orElse(ResponseEntity.notFound().build());
    }

    // GET (Lista de todas as consultas de um paciente específico)
    @Operation(summary = "Busca todas as consultas de um paciente pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consultas encontradas com sucesso."),
            @ApiResponse(responseCode = "404", description = "Consultas não encontradas.",
            content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}/consultas")
    public ResponseEntity<List<ConsultaResponseDTO>> getConsultasPorPaciente(@PathVariable Long id) {
        List<Consulta> consultas = consultaRepository.findByPacienteIdPaciente(id);
        List<ConsultaResponseDTO> consultasResponse = consultas.stream()
                .map(consultaMapper::consultaToResponse)
                .toList();
        return ResponseEntity.ok(consultasResponse);
    }


    // PUT (atualizar paciente)
    @Operation(summary = "Atualiza um paciente pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado.",
            content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> updatePaciente(@PathVariable Long id, @Valid @RequestBody PacienteRequestDTO pacienteRequestDTO) {
        return pacienteRepository.findById(id).map(paciente -> {
            paciente.setNome(pacienteRequestDTO.nome());
            paciente.setCpf(pacienteRequestDTO.cpf());
            paciente.setDataNascimento(pacienteRequestDTO.dataNascimento());
            paciente.setPlanoSaude(pacienteRequestDTO.planoSaude());
            Paciente pacienteAtualizado = pacienteRepository.save(paciente);
            return ResponseEntity.ok(pacienteMapper.pacienteToResponse(pacienteAtualizado));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE (deletar paciente)
    @Operation(summary = "Deleta um paciente pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado.",
            content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaciente(@PathVariable Long id) {
        return pacienteRepository.findById(id).map(paciente -> {
            pacienteRepository.delete(paciente);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
