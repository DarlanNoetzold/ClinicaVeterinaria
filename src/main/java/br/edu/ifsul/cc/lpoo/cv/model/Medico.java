package br.edu.ifsul.cc.lpoo.cv.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_medico")
@DiscriminatorValue("M")
public class Medico extends Pessoa{

    @Column(nullable = false)
    private String numero_crmv;

    public Medico() {
    }

    public String getNumero_crmv() {
        return numero_crmv;
    }

    public void setNumero_crmv(String numero_crmv) {
        this.numero_crmv = numero_crmv;
    }
}
