package com.example.SinisterBusterDefinitive.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "CH_CONSULTAS")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsulta;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_dentista")
    private Dentista dentista;

    @Column(name = "data_consulta")
    private String dataConsulta;

    @Column(name = "custo_consulta")
    private Double custoConsulta;


    public Long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Double getCustoConsulta() {
        return custoConsulta;
    }

    public void setCustoConsulta(Double custoConsulta) {
        this.custoConsulta = custoConsulta;
    }
}
