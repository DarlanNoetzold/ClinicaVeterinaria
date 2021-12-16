package br.edu.ifsul.cc.lpoo.clinicaVet.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_funcionario")
@DiscriminatorValue("F")
public class Funcionario extends Pessoa{

    @Column(nullable = false)
    private String numero_ctps;

    @Column(nullable = false)
    private String numero_pis;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Cargo cargo;
}
