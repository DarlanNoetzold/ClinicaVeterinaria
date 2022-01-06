package br.edu.ifsul.cc.lpoo.cv.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

@Entity
@Table(name = "tb_medico")
@DiscriminatorValue("M")
public class Medico extends Pessoa{

    @Column(nullable = false)
    private String numero_crmv;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_cadastro_Medico;

    public Medico() {
    }

    public String getNumero_crmv() {
        return numero_crmv;
    }

    public void setNumero_crmv(String numero_crmv) {
        this.numero_crmv = numero_crmv;
    }

    public Calendar getData_cadastro_Medico() {
        return data_cadastro_Medico;
    }

    public void setData_cadastro_Medico(Calendar data_cadastro) {
        this.data_cadastro_Medico = data_cadastro;
    }
}
