package com.example.SinisterBusterDefinitive.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "CH_DENTISTAS")
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDentista;

    @Column(name = "nome_dentista")
    private String nomeDentista;

    @Column(name = "cro")
    private String cro;

    @Column(name = "especialidade")
    private String especialidade;


    public Long getIdDentista() {
        return idDentista;
    }

    public void setIdDentista(Long idDentista) {
        this.idDentista = idDentista;
    }

    public String getNomeDentista() {
        return nomeDentista;
    }

    public void setNomeDentista(String nomeDentista) {
        this.nomeDentista = nomeDentista;
    }

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }


}


