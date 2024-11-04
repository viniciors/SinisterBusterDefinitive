package com.example.SinisterBusterDefinitive.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
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

}
