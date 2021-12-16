package br.edu.ifsul.cc.lpoo.clinicaVet.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_medico")
@DiscriminatorValue("M")
public class Medico extends Pessoa{

    @Column(nullable = false)
    private String numero_crmv;


}
