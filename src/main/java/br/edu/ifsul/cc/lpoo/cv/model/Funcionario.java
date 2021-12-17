package br.edu.ifsul.cc.lpoo.cv.model;

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

    public Funcionario() {
    }

    public String getNumero_ctps() {
        return numero_ctps;
    }

    public void setNumero_ctps(String numero_ctps) {
        this.numero_ctps = numero_ctps;
    }

    public String getNumero_pis() {
        return numero_pis;
    }

    public void setNumero_pis(String numero_pis) {
        this.numero_pis = numero_pis;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
