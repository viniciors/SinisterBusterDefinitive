package com.example.SinisterBusterDefinitive.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CH_SINISTROS")
public class Sinistro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSinistro;

    @Column(name = "TIPO_SINISTRO")
    private String tipoSinistro;

    @Column(name = "VALOR_SINISTRO")
    private Double valorSinistro;

    @Column(name = "DATA_SINISTRO")
    private String dataSinistro;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_dentista")
    private Dentista dentista;

    public Long getIdSinistro() {
        return idSinistro;
    }

    public void setIdSinistro(Long idSinistro) {
        this.idSinistro = idSinistro;
    }

    public String getTipoSinistro() {
        return tipoSinistro;
    }

    public void setTipoSinistro(String tipoSinistro) {
        this.tipoSinistro = tipoSinistro;
    }

    public Double getValorSinistro() {
        return valorSinistro;
    }

    public void setValorSinistro(Double valorSinistro) {
        this.valorSinistro = valorSinistro;
    }

    public String getDataSinistro() {
        return dataSinistro;
    }

    public void setDataSinistro(String dataSinistro) {
        this.dataSinistro = dataSinistro;
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
}
