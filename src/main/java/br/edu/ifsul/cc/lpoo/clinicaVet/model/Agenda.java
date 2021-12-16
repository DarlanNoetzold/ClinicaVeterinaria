package br.edu.ifsul.cc.lpoo.clinicaVet.model;


import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "tb_agenda")
public class Agenda {
    @Id
    @SequenceGenerator(name = "seq_local", sequenceName = "seq_local_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_local", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_inicio;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_fim;

    @Column(nullable = false)
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;
}
