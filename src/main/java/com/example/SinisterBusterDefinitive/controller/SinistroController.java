package com.example.SinisterBusterDefinitive.controller;

import com.example.SinisterBusterDefinitive.dto.SinistroRequestDTO;
import com.example.SinisterBusterDefinitive.dto.SinistroResponseDTO;
import com.example.SinisterBusterDefinitive.model.Dentista;
import com.example.SinisterBusterDefinitive.model.Paciente;
import com.example.SinisterBusterDefinitive.model.Sinistro;
import com.example.SinisterBusterDefinitive.repository.DentistaRepository;
import com.example.SinisterBusterDefinitive.repository.PacienteRepository;
import com.example.SinisterBusterDefinitive.repository.SinistroRepository;
import com.example.SinisterBusterDefinitive.service.SinistroMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/sinistros", produces = "application/json")
public class SinistroController {

    @Autowired
    private SinistroRepository sinistroRepository;

    @Autowired
    private SinistroMapper sinistroMapper;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DentistaRepository dentistaRepository;


    // GET (todos os sinistros)
    @Operation(summary = "Retorna todos os sinistros cadastrados no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sinistros retornados com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro ao retornar sinistros.")
    })
    @GetMapping
    public ResponseEntity<List<SinistroResponseDTO>> getAllSinistros() {
        List<Sinistro> sinistros = sinistroRepository.findAll();
        List<SinistroResponseDTO> sinistrosResponseDTO = new ArrayList<>();

        for (Sinistro sinistro : sinistros) {
            sinistrosResponseDTO.add(sinistroMapper.sinistroToResponse(sinistro));
        }

        return ResponseEntity.ok(sinistrosResponseDTO);
    }


    // GET (sinistro por id)
    @Operation(summary = "Retorna um sinistro específico cadastrado no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sinistro retornado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro ao retornar sinistro.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SinistroResponseDTO> getSinistroById(@PathVariable Long id) {
        Sinistro sinistro = sinistroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sinistro não encontrado"));

        return ResponseEntity.ok(sinistroMapper.sinistroToResponse(sinistro));
    }


    // POST (criar sinistro)
    @Operation(summary = "Cria um sinistro no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sinistro criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro ao criar sinistro.")
    })
    @PostMapping
    public ResponseEntity<SinistroResponseDTO> createSinistro(@RequestBody SinistroRequestDTO sinistroRequestDTO) {
        Paciente paciente = pacienteRepository.findById(sinistroRequestDTO.idPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        Dentista dentista = dentistaRepository.findById(sinistroRequestDTO.idDentista())
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));

        Sinistro sinistro = sinistroMapper.requestToSinistro(sinistroRequestDTO, dentista, paciente);
        Sinistro sinistroCriado = sinistroRepository.save(sinistro);

        return ResponseEntity.ok(sinistroMapper.sinistroToResponse(sinistroCriado));
    }


    // PUT (atualizar sinistro)
    @Operation(summary = "Atualiza um sinistro no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sinistro atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar sinistro.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SinistroResponseDTO> updateSinistro(@PathVariable Long id, @RequestBody SinistroRequestDTO sinistroRequestDTO) {
        Paciente paciente = pacienteRepository.findById(sinistroRequestDTO.idPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        Dentista dentista = dentistaRepository.findById(sinistroRequestDTO.idDentista())
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));

        Sinistro sinistro = sinistroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sinistro não encontrado"));

        sinistro.setTipoSinistro(sinistroRequestDTO.tipoSinistro());
        sinistro.setValorSinistro(sinistroRequestDTO.valorSinistro());
        sinistro.setDataSinistro(sinistroRequestDTO.dataSinistro());
        sinistro.setDentista(dentista);
        sinistro.setPaciente(paciente);

        Sinistro sinistroAtualizado = sinistroRepository.save(sinistro);

        return ResponseEntity.ok(sinistroMapper.sinistroToResponse(sinistroAtualizado));
    }


    // DELETE (deletar sinistro)
    @Operation(summary = "Deleta um sinistro do banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sinistro deletado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro ao deletar sinistro.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSinistro(@PathVariable Long id) {
        Sinistro sinistro = sinistroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sinistro não encontrado"));

        sinistroRepository.delete(sinistro);
        return ResponseEntity.ok().build();
    }
}
