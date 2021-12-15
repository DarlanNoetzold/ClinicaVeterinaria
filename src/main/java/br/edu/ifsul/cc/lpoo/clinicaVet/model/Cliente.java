package br.edu.ifsul.cc.lpoo.clinicaVet.model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "tb_cliente")
@DiscriminatorValue("C")
public class Cliente extends Pessoa{

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_ultima_visita;
}
